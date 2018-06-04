/*
 * Copyright (c) 2018 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.materialfilemanager.filesystem;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.util.List;

public interface File {

    enum Type {
        UNKNOWN,
        // Posix
        // https://www.gnu.org/software/libc/manual/html_node/Testing-File-Type.html
        DIRECTORY,
        CHARACTER_DEVICE,
        BLOCK_DEVICE,
        REGULAR_FILE,
        FIFO,
        SYMBOLIC_LINK,
        SOCKET
    }

    enum Permission {
        OWNER_READ,
        OWNER_WRITE,
        OWNER_EXECUTE,
        GROUP_READ,
        GROUP_WRITE,
        GROUP_EXECUTE,
        OTHERS_READ,
        OTHERS_WRITE,
        OTHERS_EXECUTE
    }

    @NonNull
    Uri getPath();

    @NonNull
    default String getName() {
        return getPath().getLastPathSegment();
    }

    @WorkerThread
    void loadInformation();

    @NonNull
    Type getType();

    default boolean isDirectory() {
        // TODO: Or archive directory.
        return getType() == Type.DIRECTORY;
    }

    boolean isListable();

    @WorkerThread
    void loadFileList();

    @NonNull
    List<File> getFileList();

    boolean equals(Object object);

    int hashCode();
}