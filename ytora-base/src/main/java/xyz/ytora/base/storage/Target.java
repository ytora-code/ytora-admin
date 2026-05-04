package xyz.ytora.base.storage;

import java.nio.file.Path;

/**
 * created by YT on 2026/1/17 00:38:27
 * <br/>
 */
public record Target(String fileId, Path dirAbsNorm, Path dataFileAbsNorm) {

}
