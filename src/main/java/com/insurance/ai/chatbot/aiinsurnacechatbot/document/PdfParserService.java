package com.insurance.ai.chatbot.aiinsurnacechatbot.document;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfParserService {

    public String extractText(MultipartFile file) throws IOException {
        try{
            PDDocument document = Loader.loadPDF(file.getBytes());
            PDFTextStripper textStripper = new PDFTextStripper();
            return textStripper.getText(document);
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract text from PDF", e);
        }
    }

}
