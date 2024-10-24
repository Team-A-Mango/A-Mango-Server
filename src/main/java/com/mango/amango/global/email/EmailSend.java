package com.mango.amango.global.email;

import com.mango.amango.global.redis.RedisUtil;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailSend {

    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;

    public void send(String email, String code) throws MessagingException {
        redisUtil.deleteDataExpire(email);

        MimeMessage mail = mailSender.createMimeMessage();

        String message = "<div style='font-family: Pretendard; background-color: #f4f4f4; padding: 20px;'>";
        message += "<div style='max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);'>";
        message += "<h1 style='font-size: 18px; font-weight: bold; margin-bottom: 20px;'>A-mango 계정 인증 코드</h1>";
        message += "<p style='font-size: 16px; line-height: 1.6; color: #333333;'>안녕하세요 A-mango 계정에 사용할 일회용 코드에 대한 요청을 받았습니다.</p>";
        message += "<div style='text-align: center; margin: 20px 0;'>";
        message += "<span style='font-size: 20px; font-weight: bold; color: #000; background-color: #fef1de; padding: 10px 20px; border-radius: 5px; display: inline-block;'>" + code + "</span>";
        message += "</div>";
        message += "<p style='font-size: 16px; line-height: 1.6; color: #333333;'>이 코드를 요청하지 않은 경우 이 메일을 무시하셔도 됩니다. 다른 사람이 실수로 귀하의 이메일 주소를 입력했을 수 있습니다.</p>";
        message += "<p style='font-size: 14px; color: #777777; margin-top: 30px;'>감사합니다, A-mango 계정 팀</p>";
        message += "</div></div>";

        mail.setSubject("🔒 [A-mango] 인증 키");
        mail.setContent(message, "text/html;charset=utf-8");
        mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        mailSender.send(mail);

        redisUtil.setDataExpire(email, code, 300);
    }
}
