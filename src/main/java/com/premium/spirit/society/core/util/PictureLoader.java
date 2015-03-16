package com.premium.spirit.society.core.util;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductCategoryDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormBO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 8. 2. 2015.
 */
public class PictureLoader {

    private ProductDisplayBO product;
    private ProductFormBO productForm;
    private List<ProductCategoryDisplayBO> productCategoryDisplayBOs;
    private boolean onlyFirst;
    private boolean onlySecond;

    public PictureLoader(ProductDisplayBO product, boolean onlyFirst) {
        this.onlyFirst = onlyFirst;
        this.product = product;
    }

    public PictureLoader(ProductDisplayBO product, boolean onlyFirst,boolean onlySecond) {
        this.onlyFirst = onlyFirst;
        this.onlySecond=onlySecond;
        this.product = product;
    }
    public PictureLoader(ProductDisplayBO product) {
        onlyFirst = true;
        this.product = product;
    }


    public PictureLoader(List<ProductCategoryDisplayBO> productCategoryDisplayBOs) {
        onlyFirst = true;
        this.productCategoryDisplayBOs = productCategoryDisplayBOs;
    }

    public PictureLoader(ProductFormBO product) {
        this.productForm = product;
        this.product = new ProductDisplayBO();
        this.product.setProductSubcategory(productForm.getProductSubcategory());
        this.product.setUrl(productForm.getUrl());
    }


    private String productString;

    public PictureLoader(String product) {
        this.productString = product;
    }

    public List<String> loadPictures() {
        List<String> pictureList = new ArrayList<>();
        String pictureFolder = null;

        if (this.product != null) {
            pictureFolder = System.getProperty("user.home")
                    + System.getProperty("file.separator") + "PremiumSpiritSociety"
                    + System.getProperty("file.separator") + "pictures"
                    + System.getProperty("file.separator") + this.product.getProductSubcategory().getProductCategory().getUrl()
                    + System.getProperty("file.separator") + this.product.getProductSubcategory().getUrl()
                    + System.getProperty("file.separator") + this.product.getUrl();


        }
        System.out.println(pictureFolder);
        /*
        else if (this.productString != null) {
            if (this.productString.equals("carousel")) {
                pictureFolder = System.getProperty("user.home")
                        + System.getProperty("file.separator") + "PremiumSpiritSociety"
                        + System.getProperty("file.separator") + "carousel";
            }
        }
        */
        File f = new File(pictureFolder);
        // pokud existuje slozka s fotkama
        File[] listOfFiles;

        listOfFiles = f.listFiles();
        if (listOfFiles != null) {
            int lengthOfListOfFiles = listOfFiles.length;

            for (int i = 0; i != lengthOfListOfFiles - 1; i++) {
                if (onlySecond){
                    onlyFirst=true;
                    onlySecond=false;
                    continue;
                }
                byte[] arr = new byte[0];
                try {
                    FileInputStream fileStream = new FileInputStream(listOfFiles[i]);
                    arr = new byte[(int) listOfFiles[i].length()];

                    /// read All bytes of File stream
                    fileStream.read(arr, 0, arr.length);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                }

                byte[] encoded = org.apache.commons.codec.binary.Base64
                        .encodeBase64(arr);
                String encodedString = new String(encoded);
                pictureList.add(encodedString);
                if (onlyFirst)
                    break;
            }
        }
        return pictureList;
    }


    public List<String> loadCategoryPictures() {
        List<String> pictureList = new ArrayList<>();
        String pictureFolder = null;

        for (ProductCategoryDisplayBO category : productCategoryDisplayBOs) {
            pictureFolder = System.getProperty("user.home")
                    + System.getProperty("file.separator") + "PremiumSpiritSociety"
                    + System.getProperty("file.separator") + "pictures"
                    + System.getProperty("file.separator") + category.getUrl();


            File f = new File(pictureFolder);
            // pokud existuje slozka s fotkama
            File[] listOfFiles;

            listOfFiles = f.listFiles();
            if (listOfFiles != null) {
                int lengthOfListOfFiles = listOfFiles.length;

                for (int i = 0; i != lengthOfListOfFiles - 1; i++) {

                    if (!listOfFiles[i].getName().equals(category.getUrl()+".jpg"))
                        continue;
                    byte[] arr = new byte[0];
                    try {
                        FileInputStream fileStream = new FileInputStream(listOfFiles[i]);
                        arr = new byte[(int) listOfFiles[i].length()];

                        /// read All bytes of File stream
                        fileStream.read(arr, 0, arr.length);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    byte[] encoded = org.apache.commons.codec.binary.Base64
                            .encodeBase64(arr);
                    String encodedString = new String(encoded);
                    pictureList.add(encodedString);
                    if (onlyFirst)
                        break;
                }
            }
        }
        return pictureList;
    }
}
