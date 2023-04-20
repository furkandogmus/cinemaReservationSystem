
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriterReader {
	public static List<String> readData(String name) throws IOException{
		FileReader rd = new FileReader(name);
		BufferedReader br = new BufferedReader(rd);
		String line;
		List<String> datas = new ArrayList<>();
		while (( line =br.readLine())!= null) {
			datas.add(line);
		}
		br.close();
		return datas;
	}
	
	public static void writeData(String name,String text) throws IOException{
		FileWriter fw = new FileWriter("assets\\data\\"+name);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(text);
		bw.close();
	}
}
