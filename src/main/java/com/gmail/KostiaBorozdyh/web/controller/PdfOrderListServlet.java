package com.gmail.KostiaBorozdyh.web.controller;

import com.gmail.KostiaBorozdyh.model.entity.Order;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Controller for Generate PDF file with Order List for Manager role
 */
@WebServlet(name = "PdfOrderListServlet", value = "/pdfOrderList")
public class PdfOrderListServlet extends HttpServlet {

    public final String FONT = "font/FreeSans.ttf";


    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String path = this.getServletContext().getRealPath("") + FONT;
        PdfFont font = PdfFontFactory.createFont(path, PdfEncodings.IDENTITY_H);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
        Document doc = new Document(pdfDoc);
        doc.getPdfDocument().setDefaultPageSize(PageSize.A4.rotate());
        Paragraph p = new Paragraph("Заявки на відправлення вантажу").setFont(font).setTextAlignment(TextAlignment.CENTER).setFontSize(14).setBold();
        doc.add(p);
        Table table = new Table(new float[]{1, 1, 1, 1, 2, 2, 2, 2, 3});
        table.setWidth(UnitValue.createPercentValue(100));
        Cell cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.add("Номер").setFont(font).setBold();
        cell.setWidthPercent(5);
        table.addCell(cell);
        cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.add("Опис").setFont(font).setBold();
        cell.setWidthPercent(20);
        table.addCell(cell);
        cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.add("Ціна").setFont(font).setBold();
        cell.setWidthPercent(5);
        table.addCell(cell);
        cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.add("Замовник").setFont(font).setBold();
        cell.setWidthPercent(10);
        table.addCell(cell);
        cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.add("Статус").setFont(font).setBold();
        cell.setWidthPercent(10);
        table.addCell(cell);
        cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.add("Місто відправлення").setFont(font).setBold();
        cell.setWidthPercent(15);
        table.addCell(cell);
        cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.add("Місто прибуття").setFont(font).setBold();
        cell.setWidthPercent(15);
        table.addCell(cell);
        cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.add("Дата Створення").setFont(font).setBold();
        cell.setWidthPercent(10);
        table.addCell(cell);
        cell = new Cell();
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.add("Дата прибуття").setFont(font).setBold();
        cell.setWidthPercent(10);
        table.addCell(cell);
        List<Order> orderList = (List<Order>) request.getSession().getAttribute("orders");
        for (Order order :
                orderList) {
            cell = new Cell();
            cell.add(order.getId().toString()).setFont(font);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
            cell = new Cell();
            cell.add(order.getDescription()).setFont(font);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
            cell = new Cell();
            cell.add(String.valueOf(order.getPrice())).setFont(font);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
            cell = new Cell();
            cell.add(String.valueOf(order.getUserLogin())).setFont(font);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
            cell = new Cell();
            cell.add(order.getPaymentStatus()).setFont(font);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
            cell = new Cell();
            cell.add(order.getCityFrom()).setFont(font);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
            cell = new Cell();
            cell.add(order.getCityTo()).setFont(font);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
            cell = new Cell();
            cell.add(String.valueOf(order.getDateCreate())).setFont(font);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
            cell = new Cell();
            cell.add(String.valueOf(order.getDateOfArrival())).setFont(font);
            cell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
            table.addCell(cell);
        }
        // Step-6 Adding Table to document
        doc.add(table);
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
