package com.dodo.marcket.http.utils.interceptor;

/**
 * author：fmd on 16/9/7
 * use:
 */
public interface DownloadProgressListener {

    /**
     *
     * @param bytesRead 已经下载或上传字节数
     * @param contentLength 总字节数
     * @param done 是否完成
     */
    void update(long bytesRead, long contentLength, boolean done);
}
