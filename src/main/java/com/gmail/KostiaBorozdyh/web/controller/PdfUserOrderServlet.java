package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.dao.OrderDao;
import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "PdfUserOrderServlet", value = "/pdfUserOrder")
public class PdfUserOrderServlet extends HttpServlet {

    public final String FONT = "D:\\servlet\\demos\\src\\main\\webapp\\font\\FreeSans.ttf";

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Get the text that will be added to the PDF
        Integer id = Integer.parseInt(request.getParameter("idOrder"));
        Order order = OrderDao.getOrder(id);
        String text = "Заявка№" + order.getId();
        String sb = "Опис: " + order.getDescription() + "\n" +
                "Вага: " + order.getWeight() + "\n" +
                "Об'єм: " + order.getVolume() + "\n" +
                "Ціна: " + order.getPrice() + "\n" +
                "Місто відправлення: " + order.getCityFrom() + "\n" +
                "Місто прибуття: " + order.getCityTo() + "\n" +
                "Адреса: " + order.getAddress() + "\n" +
                "Дата створення: " + order.getDateCreate() + "\n" +
                "Дата прибуття: " + order.getDateOfArrival() + "\n" +
                "Статус: " + order.getPaymentStatus() + "\n";
        PdfFont font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document doc = new Document(pdfDoc);
        Paragraph p = new Paragraph(text).setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold();
        doc.add(p);
        p = new Paragraph(sb).setFont(font);
        doc.add(p);

        doc.close();

        // setting some response headers
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        // setting the content type
        response.setContentType("application/pdf");
        // the contentlength
        response.setContentLength(baos.size());
        // write ByteArrayOutputStream to the ServletOutputStream
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();


    }


}
