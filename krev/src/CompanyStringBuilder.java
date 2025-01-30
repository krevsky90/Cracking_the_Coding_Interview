import java.io.*;

public class CompanyStringBuilder {
    public static void main(String[] args) throws FileNotFoundException {
        CompanyStringBuilder obj = new CompanyStringBuilder();

        String filePath = "./krev/src/companies_leetcode_data.txt";
        obj.build(filePath);
    }

    public void build(String filePath) throws FileNotFoundException {
        FileReader fileReader = new FileReader(filePath);
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(fileReader)) {
            String tempLine = null;
            while ((tempLine = br.readLine()) != null) {
                sb.append(tempLine).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String res = sb.toString();
        System.out.println(res);
    }

}
