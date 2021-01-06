package com.shanxi.coal.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.util.List;

public class ZipUtil {

    /**
     * 打包指定的文件
     *
     * @param file        待打包的文件
     * @param zipFilePath 存储压缩包的路径，包含文件名
     * @throws Exception
     *
     * @author by SPACE
     * @log create on 2020年6月15日下午2:46:49
     */
    public static void packageZip(File file, String zipFilePath) throws Exception {
        validationIsNull(file, zipFilePath);
        new ZipFile(zipFilePath).addFile(file);
    }

    /**
     * 打包指定的文件
     *
     * @param file        待打包的文件
     * @param zipFilePath 存储压缩包的路径，包含文件名
     * @param password    压缩包密码
     * @throws Exception
     *
     * @author by SPACE
     * @log create on 2020-6-15 15:09:27
     */
    public static void packageZip(File file, String zipFilePath, String password) throws Exception {
        validationIsNull(file, zipFilePath, password);
        ZipFile zipFile = new ZipFile(zipFilePath, password.toCharArray());
        zipFile.addFile(file, getZipParameters());
    }

    /**
     * 对指定的一些文件进行打包
     *
     * @param fileList    待打包的文件 list（不接收目录）
     * @param zipFilePath 存储压缩包的路径，包含文件名
     * @throws Exception
     *
     * @author by SPACE
     * @log create on 2020年6月15日15:09:37
     */
    public static void packageZip(List<File> fileList, String zipFilePath) throws Exception {
        validationIsNull(fileList, zipFilePath);
        new ZipFile(zipFilePath).addFiles(fileList);
    }

    /**
     * 对指定的一些文件进行打包
     *
     * @param fileList    待打包的文件 list
     * @param zipFilePath 存储压缩包的路径，包含文件名
     * @param password    压缩包密码
     *
     * @author by SPACE
     * @throws Exception
     * @log create on 2020年6月15日下午2:41:23
     */
    public static void packageZip(List<File> fileList, String zipFilePath, String password) throws Exception {
        validationIsNull(fileList, zipFilePath, password);
        ZipFile zipFile = new ZipFile(zipFilePath, password.toCharArray());
        zipFile.addFiles(fileList, getZipParameters());
    }

    /**
     * 打包指定的目录
     *
     * @param catalogPath 待打包的目录
     * @param zipFilePath 存储压缩包的路径，包含文件名
     * @throws Exception 如 catalogPath 非目录，则抛出此异常
     *
     * @author by SPACE
     * @log create on 2020年6月15日下午2:30:10
     */
    public static void packageZip(String catalogPath, String zipFilePath) throws Exception {
        validationIsNull(catalogPath, zipFilePath);
        new ZipFile(zipFilePath).addFolder(new File(catalogPath));
    }

    /**
     * 打包指定的目录
     *
     * @param catalogPath 待打包的目录
     * @param zipFilePath 存储压缩包的路径，包含文件名
     * @param password    压缩包密码
     * @throws Exception 如 catalogPath 非目录，则抛出此异常
     *
     * @author by SPACE
     * @log create on 2020年6月15日下午2:33:33
     */
    public static void packageZip(String catalogPath, String zipFilePath, String password) throws Exception {
        validationIsNull(catalogPath, zipFilePath, password);
        ZipFile zipFile = new ZipFile(zipFilePath, password.toCharArray());
        zipFile.addFolder(new File(catalogPath), getZipParameters());
    }

    /**
     * 解压压缩包
     *
     * @param zipFilePath  待解压的压缩包绝对路径
     * @param unzipCatalog 解压后的目录
     * @throws Exception
     *
     * @author by SPACE
     * @log create on 2020年6月15日下午3:51:07
     */
    public static void unzipAll(String zipFilePath, String unzipCatalog) throws Exception {
        validationIsNull(zipFilePath, unzipCatalog);
        new ZipFile(zipFilePath).extractAll(unzipCatalog);
    }

    /**
     * 解压带密码的压缩包
     *
     * @param zipFilePath  待解压的压缩包绝对路径
     * @param unzipCatalog 解压后的目录
     * @param password     压缩包密码
     * @throws Exception
     *
     * @author by SPACE
     * @log create on 2020年6月15日下午3:51:45
     */
    public static void unzipAll(String zipFilePath, String unzipCatalog, String password) throws Exception {
        validationIsNull(zipFilePath, unzipCatalog);
        new ZipFile(zipFilePath, password.toCharArray()).extractAll(unzipCatalog);
    }

    /**
     * 解压指定的文件
     *
     * @param zipFilePath    待解压的压缩包绝对路径
     * @param targetFilePath 目标文件相对目录，基于压缩包根目录
     * @param unzipCatalog   解压后的目录
     * @throws Exception
     *
     * @author by SPACE
     * @log create on 2020年6月15日下午3:56:15
     */
    public static void unzipTargetFile(String zipFilePath, String targetFilePath, String unzipCatalog)
            throws Exception {
        new ZipFile(zipFilePath).extractFile(targetFilePath, unzipCatalog);
    }

    /**
     * 从设置了密码的压缩包中解压指定的文件
     *
     * @param zipFilePath    待解压的压缩包绝对路径
     * @param targetFilePath 目标文件相对目录，基于压缩包根目录，
     * 							<span style="color:red">例如 msg/success/msg.txt</span>
     * @param unzipCatalog   解压后的目录
     * @param password       压缩包密码
     * @throws Exception
     *
     * @author by SPACE
     * @log create on 2020年6月15日下午3:54:36
     */
    public static void unzipTargetFile(String zipFilePath, String targetFilePath, String unzipCatalog, String password)
            throws Exception {
        new ZipFile(zipFilePath, password.toCharArray()).extractFile(targetFilePath, unzipCatalog);
    }

    /**
     * 校验参数是否为空
     *
     * @param objects 待校验的参数数组
     * @throws NullPointerException
     *
     * @author by SPACE
     * @log create on 2020年6月15日下午3:06:20
     */
    static void validationIsNull(Object... objects) throws NullPointerException {
        for (int i = 0; i < objects.length; i++) {
            if (objects[i]==null) {
                throw new NullPointerException("param is null");
            }
        }
    }

    /**
     * get ZipParameters
     *
     * @return ZipParameters
     *
     * @author by SPACE
     * @log create on 2020年6月15日下午3:05:24
     */
    public static ZipParameters getZipParameters() {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        return zipParameters;
    }

}
