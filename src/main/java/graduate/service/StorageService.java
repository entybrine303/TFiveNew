package graduate.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

<<<<<<< HEAD
	void delete(String storedFileName) throws IOException;

	Path load(String fileName);

	Resource loadAsResource(String fileName);

	void store(MultipartFile file, String storedFileName);

	String getStoredFileName(MultipartFile file, String id);

}
=======
	void delete(String storedFilename) throws IOException;

	Path load(String filename);

	Resource loadAsResource(String filename);

	void store(MultipartFile file, String storedFilename);

	String getStoredFilename(MultipartFile file, String id);

}
>>>>>>> ea3aa1b832272b24c1620c6b43bdac02341f36c4
