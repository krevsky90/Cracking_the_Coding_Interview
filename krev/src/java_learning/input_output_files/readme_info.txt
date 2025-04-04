InputStream
	FilterInputStream
		BufferedInputStream - накапливает вводимые данные в специальном буфере без постоянного обращения к устройству ввода.
	FileInputStream - reads 1 byte from File (not char, but byte! => can be used for work with any types of files: jpg, mp4 etc)

OutputStream
	FilterOutputStream
		BufferedOutputStream - накапливает выводимые байты без постоянного обращения к устройству. И когда буфер заполнен, производится запись данных.
	FileOutputStream - writes 1 byte to File (not char, but byte! => can be used for work with any types of files: jpg, mp4 etc)

Reader
	BufferedReader - reads array of chars => always wrap any Reader by BufferedReader
	InputStreamReader
		FileReader - reads 1 char => useful to work with text files

Writer
	BufferedWriter - writes array of chars => always wrap any Writer by BufferedWriter
	OutputStreamWriter
		FileWriter - writes 1 char => useful to work with text files

NOTE: BufferedOutputStream
    When you call the write method of BufferedOutputStream, the data is first written to an internal byte array (buffer) in memory.
    The size of this buffer is determined by the buffer size specified during the creation of the BufferedOutputStream (default is typically 8 KB).
    File access (actual disk I/O) happens only when the buffer is flushed, either because it is full, explicitly flushed, or when the stream is closed.
==========================================
to write text:
try (BufferedWriter wr = new BufferedWriter(new FileWriter(filePath))) {
	bw.write("Write 1st line");
} catch (..) {

}

to read text:
try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    String line = null;
    while ((line = br.read()) != null) {
        sout(line);
    }
} catch (..) {

}

to write raw data (bytes):
try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(FILE_IMAGE_PATH_CLONE));
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FILE_IMAGE_PATH))) {
        int tmp;
        while ((tmp = bis.read()) != -1) {
            bos.write(tmp);
        }
} catch (IOException e) {
    //NOTE: FileWriter can throw exception is file name is the same as name of existing directory (i.e. there is directory with name 'test_write_file.txt'
    System.out.println("can't create file");
    System.out.println(e.getLocalizedMessage());
}

