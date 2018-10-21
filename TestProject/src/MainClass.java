//import javax.net.ssl.HttpsURLConnection;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
//import java.io.OutputStream;
import java.io.InputStreamReader;

public class MainClass {

	private void PostReq(String HitURL) throws IOException
	{
		URL obj = new URL(HitURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		
		int responcecode = con.getResponseCode();
		System.out.println("Responce code is :" +responcecode + "\n");
		
		if (responcecode==200)
			PrintReply(con);		
	}
	
	private void PrintReply(HttpURLConnection con1)
	{
		System.out.println("Http Reply is :");
		BufferedReader rd;
		try {
			rd = new BufferedReader( new InputStreamReader(con1.getInputStream()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
				System.out.println(line+"\n");
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
public static void main(String[] args) throws IOException
{
	
	//Starting EXE file
	MainClass http = new MainClass();
	Runtime runtime = Runtime.getRuntime();
	Process process = runtime.exec("./exe/Get_sanity-windows-amd64.exe", null, new File("./exe"));
    
	//POST htttp URL
	String url1 = "http://localhost:9002/Get_sanity";
	http.PostReq(url1);
	
	//Closing Process
	process.destroy();
	if (process.isAlive()!= true)
	System.out.println("Process is closed");
	else
		process.destroyForcibly();
	
}

}
