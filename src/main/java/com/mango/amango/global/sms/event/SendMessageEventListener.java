package com.mango.amango.global.sms.event;

import com.mango.amango.global.sms.CoolSmsProperties;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.exception.NurigoEmptyResponseException;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.exception.NurigoUnknownException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.mango.amango.global.sms.PhoneUtil.*;

@Component
@RequiredArgsConstructor
public class SendMessageEventListener {

    private final DefaultMessageService messageService;
    private final CoolSmsProperties coolSmsProperties;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void sendMessage(SendMessageEvent sendMessageEvent) throws NurigoMessageNotReceivedException, NurigoEmptyResponseException, NurigoUnknownException {
        Message message = new Message();
        message.setFrom(formatPhoneNumber(coolSmsProperties.getToPhoneNumber()));
        message.setTo(formatPhoneNumber(sendMessageEvent.getFromPhoneNumber()));
        message.setText(sendMessageEvent.getMessage());


        messageService.send(message);
    }
}
