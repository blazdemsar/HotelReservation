package com.blazdemsar.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.Booking;
import com.blazdemsar.domain.Guest;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.layout.element.Cell; 
//import com.itextpdf.layout.element.Table;

@Service
public class MailService {

	@Autowired
	JavaMailSender  javaMailSender;
	
	//return MimeMessage
	public MimeMessage sendMessageWithAttachment(String username, String emailTo, String pathToAttachment) throws MessagingException {
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(emailTo);
		helper.setCc("synergisfremont@gmail.com");
		helper.setSubject("Test Booking Email");
		helper.setText("Hi " + username + "!\n\nThank you for choosing our Booking Services! We have attached your reservation details below."+
					   "Should you have any questions don't hesitate to contact our customer service team." + "\n\nThank you and have a great day, "+ "\nBlazing Bookings");
		
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment("Reservation_Confirmation.pdf", file);
		
		javaMailSender.send(message);
		
		return message;
		
	}
	
	// return MimeMessage
	public MimeMessage bookingConfirmationPdfCreator(Booking booking, String emailTo, String username, String hotelName, String hotelAddrL1, String hotelCity, String hotelState, String hotelImage) throws DocumentException, IOException, MessagingException {
		
		System.out.println("Inside of MailService.bookingConfirmationPdfCreator().........");
		
		String pathToAttachment = "D:/BookingPDFs/Reservation_Confirmation_id_"+booking.getBookingId()+".pdf";
		
		Document document = new Document();
		
		PdfWriter.getInstance(document, new FileOutputStream(pathToAttachment));
				
		document.open();
		
		Font title = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
		Font subtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
		Font paragraphs = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
		
		Paragraph ttl = new Paragraph("Thank you for your hotel reservation " + username + "!", title);
		ttl.setSpacingAfter(10f);
		document.add(ttl);
		
		Paragraph hotelInfo = new Paragraph("Hotel Address:", subtitle);
		document.add(hotelInfo);
		
		Paragraph hotelNm = new Paragraph(hotelName+",", paragraphs);
		Paragraph hotelAddr = new Paragraph(hotelAddrL1+",", paragraphs);
		Paragraph hotelCt = new Paragraph(hotelCity+",", paragraphs);
		Paragraph hotelStt = new Paragraph(hotelState+"", paragraphs);
		
		hotelStt.setSpacingAfter(10f);
		
		document.add(hotelNm);
		document.add(hotelAddr);
		document.add(hotelCt);
		document.add(hotelStt);
		
		Image image = Image.getInstance(new URL(hotelImage));
		image.scaleAbsolute(530f, 310f);
		image.setSpacingAfter(10f);
		document.add(image);
		
		List<Paragraph> chunks = new ArrayList<>();
		
		//chunks.add(new Paragraph("Thank you for your hotel reservation " + username + "!", title));
		
		Paragraph subttl = new Paragraph("Reservation details:", subtitle);
		subttl.setSpacingAfter(10f);
		
		chunks.add(subttl);
		chunks.add(new Paragraph("Booking number: " + booking.getBookingId(), paragraphs));
		chunks.add(new Paragraph("Booking Made On: " + booking.getBookedOnDate(), paragraphs));
		chunks.add(new Paragraph("Check In By: " + booking.getCheckInDate() + " 7PM", paragraphs));
		chunks.add(new Paragraph("Check Out By: " + booking.getCheckOutDate() + " 12PM", paragraphs));
		
		for (Paragraph p : chunks) {
			document.add(p);
		}
		
		Paragraph contact = new Paragraph("Contact: " + booking.getCustomerMobile() + " / " + emailTo, paragraphs);
		contact.setSpacingAfter(10f);
		document.add(contact);
		
		Paragraph dicount = new Paragraph("Discount: " + booking.getDiscount() + "%", paragraphs);
		Paragraph totalPrice = new Paragraph("Total Price: $" + booking.getPrice(), paragraphs);
		totalPrice.setSpacingAfter(10f);
		document.add(dicount);
		document.add(totalPrice);
		
		Set<Guest> guests = booking.getGuests();
		
		/*
		 * float[] columnWidths = {100f, 100f, 100f, 100f};
		 * Table table = new Table(columnWidths);
		 * 
		 * table.addCell(new Cell().add("First Name"));
		 * table.addCell(new Cell().add("Last Name"));
		 * table.addCell(new Cell().add("Gender"));
		 * table.addCell(new Cell().add("Age"));
		 * 
		 * for (Guest g : guests) {
		 * 	table.addCell(new Cell().add(g.getFirstName()));
		 *  table.addCell(new Cell().add(g.getLastName()));
		 *  table.addCell(new Cell().add(g.getGender()));
		 *  table.addCell(new Cell().add("" + g.getAge()));
		 * }
		 * 
		 * document.add(table);
		 */
		
		Paragraph guestsPara = new Paragraph("Guest/s: ", subtitle);
		guestsPara.setSpacingAfter(10f);
		document.add(guestsPara);
		
		for (Guest g : guests) {
			Paragraph guestFName = new Paragraph("First Name: " + g.getFirstName(), paragraphs);
			document.add(guestFName);
			Paragraph guestLName = new Paragraph("Last Name: " + g.getLastName(), paragraphs);
			document.add(guestLName);
			Paragraph guestGender = new Paragraph("Gender: " + g.getGender(), paragraphs);
			document.add(guestGender);
			Paragraph guestAge = new Paragraph("Age: " + g.getAge(), paragraphs);
			guestAge.setSpacingAfter(10f);
			document.add(guestAge);
		}
		
		document.close();
		System.out.println("Confirmation PDF created!");
		
		MimeMessage message = sendMessageWithAttachment(username, emailTo, pathToAttachment);
		
		return message;
		
	}
}
