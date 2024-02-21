package graduate.service.imp;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import graduate.config.StorageProperties;
import graduate.exception.StorageException;
import graduate.exception.StorageFileNotFoundException;
import graduate.service.StorageService;


@Service
public class FileSystemStorageSericeImpl implements StorageService{
	private final Path rootLocation;
	
	@Override
	public String getStoredFileName(MultipartFile file, String id) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		
		return "p" + id + "." + ext;
	}
	
	public FileSystemStorageSericeImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}
	
	 public void storeImageWithResize(MultipartFile file, String storedFilename, int width, int height) {
	        try {
	            if (file.isEmpty()) {
	                throw new StorageException("Failed to store empty file");
	            }
	         // Get the file extension from the original filename
	            String fileExtension = getFileExtension(file.getOriginalFilename());
	            Path destinationFile = this.rootLocation.resolve(Paths.get(storedFilename))
	                    .normalize().toAbsolutePath();

	            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
	                throw new StorageException("Cannot store file outside current directory");
	            }

	            try (InputStream inputStream = file.getInputStream()) {
	                BufferedImage originalImage = ImageIO.read(inputStream);
	                BufferedImage resizedImage = resizeImage(originalImage, width, height);

	                Files.createDirectories(destinationFile.getParent());
	                ImageIO.write(resizedImage, fileExtension, destinationFile.toFile());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new StorageException("Failed to store and resize image", e);
	        }
	    }
	 private String getFileExtension(String filename) {
	        return filename.substring(filename.lastIndexOf(".") + 1);
	    }

	    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
	        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
	        resizedImage.getGraphics().drawImage(originalImage, 0, 0, width, height, null);
	        return resizedImage;
	    }
	
	@Override
	public void store(MultipartFile file, String storedFilename) {
		try {
			if(file.isEmpty()){
				throw new StorageException("Failed to store empty file");
			}
			Path destinationFile = this.rootLocation.resolve(Paths.get(storedFilename))
					   .normalize().toAbsolutePath();
			if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("Cannot store file outside current directory");
			}
			try(InputStream inputSteam = file.getInputStream()) {
				Files.copy(inputSteam, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new StorageException("Failed to store file", e);
		}
	}
	
	@Override
	public Resource loadAsResource(String filename){
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}
			throw new StorageFileNotFoundException("Could to read file" + filename);
		} catch (Exception e) {
			throw new StorageFileNotFoundException("Could to read file" + filename);
		}
		
	}
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(rootLocation);
	}
	
	@Override
	public void delete(String storedFilename ) throws IOException {
		 Path destinationFile = rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
		 
		 Files.delete(destinationFile);
	}
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (Exception e) {
			throw new StorageException("Could not initialize strongage", e);
		}
	}
	
}
	
	


