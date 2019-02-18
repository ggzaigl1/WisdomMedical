package com.hjy.wisdommedical.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * describe： TODO </br>
 * Created by fangs on 2018/11/29 10:49.
 */
public class UpLoadUtils {

    /**
     * 把 File集合转化成 MultipartBody.Part集合
     *
     * @param files File列表或者 File 路径列表
     * @param <T>   泛型
     * @return MultipartBody.Part列表（retrofit 多文件文件上传）
     */
    public static <T> List<MultipartBody.Part> filesToMultipartBodyPart(List<T> files) {
        List<MultipartBody.Part> parts = new ArrayList<>();

        File file;
        for (T t : files) {//访问手机端的文件资源，保证手机端sdcdrd中必须有这个文件

            if (t instanceof File) file = (File) t;
            else if (t instanceof String)
                file = new File((String) t);//访问手机端的文件资源，保证手机端sdcdrd中必须有这个文件
            else break;

            String path = file.getPath();
            String fileStr = path.substring(path.lastIndexOf(".") + 1);

            RequestBody requestBody = RequestBody.create(MediaType.parse("image/" + fileStr), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestBody);
            parts.add(part);
        }

        return parts;
    }
}
