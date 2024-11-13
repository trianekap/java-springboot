import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileDownloader {
	public static void main(String []args){
		String[] fileUrls = {
			"https://example.com/file1.zip",
			"https://example.com/file2.zip",
			"https://example.com/file3.zip",
		};

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (String fileUrl : fileUrls){
			executorService.execute(() -> {
				try {
					downloadFile(fileUrl);
				} catch (Exception e) {
					System.out.println("Gagal mengunduh file: " + fileUrl);
				}
			});
		}

		executorService.shutdown();
	}

	private static void downloadFile(String fileUrl) throws Exception {
		URL url = new URL(fileUrl);
		try (InputStream in = url.openStream(); FileOutputStream out = new
		FileOutputStream(getFileName(fileUrl))) {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			System.out.println("File berhasil diunduh: " + fileUrl);
		}

	}

	private static String getFileName(String fileUrl){
		int lastIndex = fileUrl.lastIndexOf("/");
		return fileUrl.substring(lastIndex + 1);
	}
}