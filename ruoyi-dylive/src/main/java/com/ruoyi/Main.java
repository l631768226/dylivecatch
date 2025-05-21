package com.ruoyi;

import io.minio.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            // 创建 Minio 客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint("http://hitsoftzhhb.com:9000")
                    .credentials("zhhbusr", "Zhhbusr@eia")
                    .build();

//            String bucketName = "outfalldt";
//
//            List<String> folders = new ArrayList<>();
//            // 遍历桶中的对象以查找文件夹
//
//            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
//
//            for (Result<Item> result : results){
//                Item item = result.get();
//                String objectName = item.objectName();
//                int slashIndex = objectName.indexOf('/');
//                if (slashIndex!= -1) {
//                    String folder = objectName.substring(0, slashIndex);
//                    if (!folders.contains(folder)) {
//                        folders.add(folder);
//                    }
//                }
//            }
//
//            List<String> nearestFolders = filterAndReverseDates(folders);
//
//            List<String> dateStrList = new ArrayList<>();
//            List<String> dataList = new ArrayList<>();
//
//            for(int i = 0; i < 7; i ++){
//                String folder = nearestFolders.get(i);
//                Iterable<Result<Item>> folderResults = minioClient.listObjects(
//                        ListObjectsArgs.builder()
//                                .bucket(bucketName)
//                                .prefix(folder + "/")
//                                .recursive(false)
//                                .build());
//                int count = 0;
//                for (Result<Item> result : folderResults){
//                    count ++;
//                }
//                dateStrList.add(folder);
//                dataList.add(String.valueOf(count));
//            }


//                List<String> paths = new ArrayList<>();
//                paths.add("");
//                while (!paths.isEmpty()) {
//                    String path = paths.remove(0);
//                    Iterable<Result<Item>> results = minioClient.listObjects(bucketName, path);
//                    for (Result<Item> result : results) {
//                        Item item = result.get();
//                        String objectName = "";
//                        try {
//                            objectName = URLDecoder.decode(item.objectName(), "UTF-8");
//                        } catch (UnsupportedEncodingException e) {
//                            objectName = item.objectName();
//                        }
//                        if (item.isDir()) {
//                            System.out.println(objectName);
//                            System.out.println("Bucket Name: " + bucketName + " path: " + path + " Folder: " + objectName);
//                            paths.add(objectName);
//                        } else {
//                            System.out.println("Bucket Name: " + bucketName + " path: " + path + " File: " + objectName);
//                        }
//                    }
//                }

            // 获取所有桶
            for (Bucket bucket : minioClient.listBuckets()) {
                System.out.println("Bucket Name: " + bucket.name());
                String bucketName = bucket.name();

                Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
                for (Result<Item> result : results) {
                    Item item = result.get();
                    String objectName = item.objectName();
                    try {
                        objectName = URLDecoder.decode(item.objectName(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        objectName = item.objectName();
                    }
                    if (item.isDir()){
                        System.out.println("Bucket Name: " + bucketName + " Directory: " + objectName);

                        Iterable<Result<Item>> listObjects = minioClient.listObjects(bucketName, objectName);
                        for (Result<Item> listObject : listObjects) {
                            Item listItem = listObject.get();
                            String objectFileName = "";
                            try {
                                objectFileName = URLDecoder.decode(listItem.objectName(), "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                objectFileName = listItem.objectName();
                            }
                            if (listItem.isDir()) {
                                System.out.println(objectFileName);
                                int fileType = detectFileType(objectFileName);
                                long size = listItem.size();
                                System.out.println("Bucket Name: " + bucketName + " path: "  + " Folder: " + objectFileName + " Type: " + fileType + " Size: " + size);
//                                paths.add(objectName);
                            } else {
                                int fileType = detectFileType(objectFileName);
                                long size = listItem.size();
                                System.out.println("Bucket Name: " + bucketName + " path: "  + " File: " + objectFileName + " Type: " + fileType + " Size: " + size);

                            }
                        }

                    }else{
                        int fileType = detectFileType(objectName);
                        long size = item.size();
                        System.out.println("Bucket Name: " + bucketName + " File: " + objectName + " Type: " + fileType + " Size: " + size);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String getFileExtension(String filePath) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < filePath.length() - 1) {
            return filePath.substring(dotIndex + 1);
        }
        return null;
    }

    public static int detectFileType(String filePath) {
        String extension = getFileExtension(filePath);
        if (extension == null) {
            return 0; // 假设无后缀名视为文件夹
        }

        switch (extension.toLowerCase()) {
            case "doc":
            case "docx":
                return 1;
            case "xls":
            case "xlsx":
                return 2;
            case "pdf":
                return 3;
            case "ppt":
            case "pptx":
                return 4;
            case "txt":
                return 5;
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
            case "bmp":
                return 6;
            case "mp4":
            case "avi":
            case "mkv":
                return 7;
            case "apk":
                return 8;
            case "mp3":
            case "wav":
            case "flac":
                return 9;
            default:
                return 0;
        }
    }

    private static List<String> filterAndReverseDates(List<String> originalList) {
        List<String> dateStrings = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (String str : originalList) {
            try {
                Date date = sdf.parse(str);
                if (date!= null) {
                    dateStrings.add(str);
                }
            } catch (Exception e) {
                // 如果不是日期格式字符串，则忽略
            }
        }

        // 倒序排序日期字符串列表
        Collections.reverse(dateStrings);

        return dateStrings;
    }
}


//        String str = "谁是我们的敌人？谁是我们的朋友？这个问题是革命的首要问题。中国过去一切革命斗争成效甚少，其基本原因就是因为不能团结真正的朋友，以攻击真正的敌人。革命党是群众的向导，在革命中未有革命党领错了路而革命不失败的。我们的革命要有不领错路和一定成功的把握，不可不注意团结我们的真正的朋友，以攻击我们的真正的敌人。我们要分辨真正的敌友，不可不将中国社会各阶级的经济地位及其对于革命的态度，作一个大概的分析。\n" +
//                "　　中国社会各阶级的情况是怎样的呢？\n" +
//                "　　地主阶级和买办阶级。在经济落后的半殖民地的中国，地主阶级和买办阶级完全是国际资产阶级的附庸，其生存和发展，是附属于帝国主义的。这些阶级代表中国最落后的和最反动的生产关系，阻碍中国生产力的发展。他们和中国革命的目的完全不兼容。特别是大地主阶级和大买办阶级，他们始终站在帝国主义一边，是极端的反革命派。其政治代表是国家主义派[1]和国民党右派。\n" +
//                "　　中产阶级。这个阶级代表中国城乡资本主义的生产关系。中产阶级主要是指民族资产阶级，他们对于中国革命具有矛盾的态度：他们在受外资打击、军阀压迫感觉痛苦时，需要革命，赞成反帝国主义反军阀的革命运动；但是当着革命在国内有本国无产阶级的勇猛参加，在国外有国际无产阶级的积极援助，对于其欲达到大资产阶级地位的阶级的发展感觉到威胁时，他们又怀疑革命。其政治主张为实现民族资产阶级一阶级统治的国家。有一个自称为戴季陶[2]“真实信徒”的，在北京《晨报》[3]上发表议论说：“举起你的左手打倒帝国主义，举起你的右手打倒共产党。”这两句话，画出了这个阶级的矛盾惶遽状态。他们反对以阶级斗争学说解释国民党的民生主义，他们反对国民党联俄和容纳共产党[4]及左派分子。但是这个阶级的企图——实现民族资产阶级统治的国家，是完全行不通的，因为现在世界上的局面，是革命和反革命两大势力作最后斗争的局面。这两大势力竖起了两面大旗：一面是红色的革命的大旗，第三国际[5]高举着，号召全世界一切被压迫阶级集合于其旗帜之下；一面是白色的反革命的大旗，国际联盟[6]高举着，号召全世界一切反革命分子集合于其旗帜之下。那些中间阶级，必定很快地分化，或者向左跑入革命派，或者向右跑入反革命派，没有他们“独立”的余地。所以，中国的中产阶级，以其本阶级为主体的“独立”革命思想，仅仅是一个幻想。";
//
//        //生成词云
//        BufferedImage bufferImage = MyWordCloudHelper.wordCloud(str);
//
//        // 使用Base64进行编码
//        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
//            ImageIO.write(bufferImage, "png", byteArrayOutputStream);
//            Base64.Encoder encoder = Base64.getEncoder();
//            String base64Image = encoder.encodeToString(byteArrayOutputStream.toByteArray());
//            System.out.println("data:image/png;base64,"+ base64Image);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }