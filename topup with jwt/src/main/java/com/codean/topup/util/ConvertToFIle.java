package com.codean.topup.util;

import com.codean.topup.models.dtos.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class ConvertToFIle {
    public void saveUserDtoListToTxt(List<UserDto> userDtoList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        File file = new File("D:\\coba penyimpanan file\\codean.txt");

        for (UserDto dto : userDtoList) {
            //mengonversi objek ke JSON dan menambahkannya ke StringBuilder
            sb.append(objectMapper.writeValueAsString(dto));
            //menambahkan separator di setiap data
            sb.append(System.lineSeparator());
            objectMapper.writer();
        }
        //menulis isi string builder ke file
        //Files.write(Paths.get(file.toURI()), sb.toString().getBytes(StandardCharsets.UTF_8));

        objectMapper.writeValue(file, sb);

    }

    public void saveUserDtoListToPdf(List<UserDto> userDtoList) throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("D:\\coba penyimpanan file\\users.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);

        for (UserDto user : userDtoList) {
            String userJson = convertToJson(user);
            Paragraph paragraph = new Paragraph(userJson, font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(100);
            document.add(paragraph);
        }

        document.close();

    }

    public void saveUserDtoListToDocx(List<UserDto> userDtoList) throws Docx4JException {
        WordprocessingMLPackage wordPakage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPakage.getMainDocumentPart();
        mainDocumentPart.addStyledParagraphOfText("Title", "TEST CREATE JSON TO WORD");

        for (UserDto dto : userDtoList){
            String userJson = convertToJson(dto);
            mainDocumentPart.addParagraphOfText(userJson);
        }

        File exportFile = new File("D:\\coba penyimpanan file\\users.docx");
        wordPakage.save(exportFile);

    }

    public void saveUserDtoListToTxt(UserDto userDto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        File file = new File("D:\\coba penyimpanan file\\users.txt");


        //mengonversi objek ke JSON dan menambahkannya ke StringBuilder
        sb.append(objectMapper.writeValueAsString(userDto));
        //menambahkan separator di setiap data
        sb.append(System.lineSeparator());

        //menulis isi string builder ke file
        Files.write(Paths.get(file.toURI()), sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    public void saveUserDtoListToPdf(UserDto userDto) throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("D:\\coba penyimpanan file\\users.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);

        String userJson = convertToJson(userDto);
        Paragraph paragraph = new Paragraph(userJson, font);
        document.add(paragraph);


        document.close();

    }

    public void saveUserDtoListToDocx(UserDto userDto) throws Docx4JException {
        WordprocessingMLPackage wordPakage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPakage.getMainDocumentPart();
        mainDocumentPart.addStyledParagraphOfText("Title", "TEST CREATE JSON TO WORD");

        String userJson = convertToJson(userDto);
        mainDocumentPart.addParagraphOfText(userJson);

        File exportFile = new File("D:\\coba penyimpanan file\\users.docx");
        wordPakage.save(exportFile);

    }

    private String convertToJson(UserDto user) {
        return new Gson().toJson(user);
    }
}
