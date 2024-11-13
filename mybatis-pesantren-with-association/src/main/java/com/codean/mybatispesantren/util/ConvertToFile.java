package com.codean.mybatispesantren.util;

import com.codean.mybatispesantren.model.dtos.presensi.PresensiDTO;
import com.codean.mybatispesantren.model.entity.Presensi;
import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.List;

@Configuration
public class ConvertToFile {

    public void saveToPdf(List<PresensiDTO> presensiList) {

        try {
            // creation of the document with a certain size and certain margins
            Document document = new Document(PageSize.A4, 20, 20, 20, 20);

            Paragraph paragraph = new Paragraph();
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(15);
            Font judul = new Font(Font.FontFamily.TIMES_ROMAN,20.0f,Font.UNDERLINE,BaseColor.BLACK);

            paragraph.add(new Chunk("LAPORAN KEHADIRAN KEGIATAN SANTRI", judul));
            paragraph.setSpacingAfter(60f);

            // creating table and set the column width
            PdfPTable table = new PdfPTable(5);
            float widths[] = { 1, 3, 3, 3, 3 };
            table.setWidths(widths);
            table.setHeaderRows(1);
            table.setHorizontalAlignment(PdfPTable.ALIGN_CENTER);

            BaseColor color = WebColors.getRGBColor("FFFF00");

            // add cell of table - header cell
            PdfPCell cell = new PdfPCell(new Phrase("ID"));
            cell.setBackgroundColor(color);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setFixedHeight(30f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nama Santri"));
            cell.setBackgroundColor(color);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setFixedHeight(30f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nama Kegiatan"));
            cell.setBackgroundColor(color);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setFixedHeight(30f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Tanggal"));
            cell.setBackgroundColor(color);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setFixedHeight(30f);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Status"));
            cell.setBackgroundColor(color);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setFixedHeight(30f);
            table.addCell(cell);

            Phrase ph;
            for (PresensiDTO presensiDTO : presensiList) {

                String a = String.valueOf(presensiDTO.getId());

                cell = new PdfPCell();
                ph = new Phrase(a);
                cell.addElement(ph);
                cell.setHorizontalAlignment(cell.getHorizontalAlignment());
                table.addCell(cell);

                cell = new PdfPCell();
                ph = new Phrase(presensiDTO.getNama_santri());
                cell.addElement(ph);
                cell.setHorizontalAlignment(cell.getHorizontalAlignment());
                table.addCell(cell);

                cell = new PdfPCell();
                ph = new Phrase(presensiDTO.getNama_kegiatan());
                cell.addElement(ph);
                cell.setHorizontalAlignment(cell.getHorizontalAlignment());
                table.addCell(cell);

                cell = new PdfPCell();
                ph = new Phrase(presensiDTO.getTanggal().toString());
                cell.addElement(ph);
                cell.setHorizontalAlignment(cell.getHorizontalAlignment());
                table.addCell(cell);

                cell = new PdfPCell();
                ph = new Phrase(presensiDTO.getStatus());
                cell.addElement(ph);
                cell.setHorizontalAlignment(cell.getHorizontalAlignment());
                table.addCell(cell);

            }

            PdfWriter.getInstance(document, new FileOutputStream("D:\\coba penyimpanan file\\daftar-kehadiran.pdf"));
            document.open();
            document.add(paragraph);
            document.add(table);
            document.close();
            System.out.println("Document success created");

        } catch (DocumentException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

//    public void saveUserDtoListToPdf(List<PresensiDTO> presensiDTOList) throws IOException, DocumentException {
//
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("D:\\coba penyimpanan file\\kehadiran.pdf"));
//
//        document.open();
//        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
//
//        float [] pointColumnWidths = {100F, 100F, 100F};
//        Table table = new Table(5);
//        table.addCell("ID");
//        table.addCell("Status");
//        table.addCell("Nama Santri");
//        table.addCell("Tanggal");
//
//
//        for (PresensiDTO presensiDTO : presensiDTOList) {
//            Presensi presensi = convertDtoToEntity(presensiDTO);  // Convert DTO to Entity
//            String userJson = convertToJson(presensi);
//            Paragraph paragraph = new Paragraph(userJson, font);
//            paragraph.setAlignment(Element.ALIGN_CENTER);
//            paragraph.setSpacingAfter(10);
//            document.add(paragraph);
//        }
//
//
//        document.close();
//    }
//
//    private Presensi convertDtoToEntity(PresensiDTO dto) {
//        Presensi entity = new Presensi();
//        entity.setId(dto.getId());
//        entity.setStatus(dto.getStatus());
//        entity.setNamaSantri(dto.getNama_santri());
//        entity.setNamaKegiatan(dto.getNama_kegiatan());
//        entity.setTanggal(dto.getTanggal());
//        return entity;
//    }

    private String convertToJson(Presensi presensi) {
        return new Gson().toJson(presensi);
    }
}
