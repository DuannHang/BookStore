package com.duanhang.bookStore.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendJMail {
	
		public static boolean sendMail(String email, String emailMsg) throws Exception {
		
		String from = "duan_hang@foxmail.com"; 				//�ʼ������˵��ʼ���ַ
		String to = email; 								//�ʼ������˵��ʼ���ַ
		final String username = "duan_hang@foxmail.com";  	//�����˵��ʼ��ʻ�
		final String password = "pscgrajmptiidieh";   		//�����˵��ʼ�����
		
		//����Properties����,���û�����Ϣ
		Properties props = new Properties();
		//�����ʼ��������ĵ�ַ
		props.setProperty("mail.transport.protocol", "smtp");//���÷����ʼ�ʹ�õ�Э��
		props.setProperty("mail.smtp.host", "smtp.qq.com"); // ָ����smtp������
		props.setProperty("mail.smtp.auth", "true");
		
		//����ssl���� ���530 Error: A secure connection is requiered(such as ssl).
		MailSSLSocketFactory ssl = new MailSSLSocketFactory();
		ssl.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable","true");
		props.put("mail.smtp.ssl.socketFactory", ssl);
		
		
		//����Session����,session�����ʾ�����ʼ��Ļ�����Ϣ
		Session session = Session.getInstance(props);
		//�������������Ϣ
		session.setDebug(true);
		try {
			//Message��ʵ�������ʾһ������ʼ�
			MimeMessage message = new MimeMessage(session);
			//���÷����˵ĵ�ַ
			message.setFrom(new InternetAddress(from));
			//��������
			message.setSubject("Duann.��BookStore�û�����");
			//�����ʼ����ı�����
			//message.setText("Welcome to JavaMail World!");
			message.setContent(emailMsg,"text/html;charset=utf-8");
			//��session�Ļ����л�ȡ�����ʼ��Ķ���
			Transport transport=session.getTransport();
			//�����ʼ�������
			transport.connect("smtp.qq.com",465, username, password);
			//�����ռ��˵�ַ,��������Ϣ
			transport.sendMessage(message,new Address[]{new InternetAddress(to)});
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
