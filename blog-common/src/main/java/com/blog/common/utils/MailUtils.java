package com.blog.common.utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by Dell on 2018/10/10.
 */
public class MailUtils {
    //发件人邮箱和密码
    public static String sendEmail="1747100270@qq.com";
    public static String sendPassword="lsx18772116092";

    //发件人邮箱的SMTP服务器地址，网易126邮箱的SMTP服务器地址为smtp.126.com   qq邮箱的smtp服务器地址为smtp.qq.com
    public static String emailSMTPHost="smtp.qq.com";

    /**
     * 发送邮件
     * @param subject 主题
     * @param receiveEmail 接收邮箱
     * @param sendPerson   //发送人
     * @param receivePerson  //接收人
     * @param content   //邮件内容
     * @throws Exception
     */
    public static void sendMail(String subject,String receiveEmail,String sendPerson,String receivePerson,String content) throws Exception{
        //用于连接邮件服务器的参数配置
        Properties props=new Properties();
        props.setProperty("mail.transport.protocol","smtp");  //使用协议(JavaMail规范要求)
        props.setProperty("mail.smtp.host",emailSMTPHost);  //发件人的SMTP服务器地址
        props.setProperty("mail.smtp.auth","true");     //需要请求认证

        //根据配置创建会话对象，用于和邮件服务器交互
        Session session=Session.getInstance(props);
        //设置为debug模式，可以船好看详细的发送log
        session.setDebug(true);
        //创建一封邮件
        //MimeMessage message=createMimeMessage(session,sendEmail,receiveAccount);
        //1.创建一封邮件
        MimeMessage message=new MimeMessage(session);
        //2.From:发件人
        message.setFrom(new InternetAddress(sendEmail,sendPerson,"UTF-8"));
        //3.To:收件人
        message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(receiveEmail,receivePerson,"UTF-8"));
        //主题
        message.setSubject(subject,"UTF-8");
        //邮件内容
        message.setContent(content,"text/html;charset=UTF-8");
        //发送时间
        message.setSentDate(new Date());
        message.saveChanges();

        Transport transport=session.getTransport();

        transport.connect(sendEmail,sendPassword);
        transport.sendMessage(message,message.getAllRecipients());
        transport.close();
    }
}
