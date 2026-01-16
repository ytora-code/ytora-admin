package xyz.ytora.base.storage;

import lombok.Data;

import java.nio.file.Path;

/**
 * created by YT on 2026/1/17 00:38:27
 * <br/>
 */
@Data
public class Target {

    final String fileId;
    final Path dirAbsNorm;
    final Path dataFileAbsNorm;

    public Target(String fileId, Path dirAbsNorm, Path dataFileAbsNorm) {
        this.fileId = fileId;
        this.dirAbsNorm = dirAbsNorm;
        this.dataFileAbsNorm = dataFileAbsNorm;
    }

}
