package com.jicg.jman.web.controller.web;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.EAN13Writer;
import lombok.extern.slf4j.Slf4j;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author jicg on 2020/7/6
 */
@RequestMapping("/b")
@Controller
@Slf4j
public class BarCodeController {

    @GetMapping("/barcode2")
    public void getBarCode2(@RequestParam("barcode") String barcode,
                            HttpServletResponse response) throws Exception {
//        BufferedImage image = QrCodeUtil.generate(barcode, 250, 40);
        log.info(barcode);
        BufferedImage image = generateEAN13BarcodeImage2(barcode);
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
    }

    public static BufferedImage generateEAN13BarcodeImage2(String barcodeText) throws Exception {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 0, 60);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

}
