package com.playtika.file.reader;

import com.playtika.text.analyzer.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Files.readAttributes;
import static java.util.Arrays.asList;
import static java.util.Map.Entry;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class FileReader {
	private static final Logger LOG = LoggerFactory.getLogger(FileReader.class);
	private final File startDirectory;

	public FileReader(String startDirectoryPath) throws FileNotFoundException, NotDirectoryException {
		startDirectory = new File(startDirectoryPath);
		if(!startDirectory.exists()) {
			throw new FileNotFoundException("File with next path doesn't exist: [" + startDirectoryPath + "]");
		}
		if(!startDirectory.isDirectory()) {
			throw new NotDirectoryException(startDirectory.getAbsolutePath());
		}
	}

	public static void main(String[] args) throws IOException {
		File file = Paths.get("src", "main", "resources", "text", "text.txt").toFile();
		FileReader.copyFile(file);
		LOG.debug("Copying of file [{}] was finished", file);
		Map<String, Integer> wordFrequencies = new FileReader(Paths.get("src", "main", "resources", "text").toString()).getWordFrequenciesForFiles();
		System.out.println(wordFrequencies);
	}

	public Map<String, Integer> getWordFrequenciesForFiles() {
		return getAllIncludedFiles().stream()
				.map(this::getStringFromFileAndPrintInformationAboutFile)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(Text::new)
				.flatMap(text -> text.getWordFrequencies().entrySet().stream())
				.collect(groupingBy(Entry::getKey, summingInt(Entry::getValue)));
	}

	public static void printFileInformation(File file) throws IOException {
		BasicFileAttributes fileAttributes = readAttributes(file.toPath(), BasicFileAttributes.class);
		LOG.info("File path: {} | size: {} Bytes | creation date: {}",
				file.getAbsolutePath(),
				file.length(),
				new SimpleDateFormat("dd-MM-yyyy HH:mm").format(fileAttributes.creationTime().toMillis()));
	}

	public static void copyFile(File file) throws IOException {
		File newFile = new File(file.getPath().replaceAll("(?s)(\\.)(?!.*?(\\.))", "New."));
		if (newFile.exists()) {
			newFile.delete();
		}
		try (InputStream in = new BufferedInputStream(new FileInputStream(file));
			 OutputStream out = new BufferedOutputStream(new FileOutputStream(newFile))) {
			byte[] buffer = new byte[1024];
			int length = in.read(buffer);
			while (length > 0) {
				out.write(buffer, 0, length);
				length = in.read(buffer);
			}
		}
	}

	private Optional<String> getStringFromFileAndPrintInformationAboutFile(File file) {
		try {
			printFileInformation(file);
			return Optional.ofNullable(new String(readAllBytes(file.toPath()), UTF_8));
		} catch (IOException e) {
			return Optional.empty();
		}
	}

	private List<File> getAllIncludedFiles() {
		return asList(startDirectory.listFiles(File::isFile));
	}
}
