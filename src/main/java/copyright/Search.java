package copyright;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {
	private ArrayList<String[]> d=new ArrayList();
	public ArrayList<String[]> getD()
	{
		return d;
	}
	public void getWmic() 
	{
		try 
		{
			ArrayList<String> wmic=new ArrayList<String>();
            Process process = Runtime.getRuntime().exec("cmd /c wmic product get Caption");
            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
            String tmp = null;
            process.getOutputStream().close();
            while((tmp=bufferedReader.readLine())!=null)
            {
                tmp=tmp.replace(" ","");
                wmic.add(tmp);
            }
            for(int i=0;i<wmic.size();i++)
            {
                String[] data_wmic=new String[2];
                data_wmic[0]="WMIC";
                data_wmic[1]=wmic.get(i);
                if(data_wmic[1].length()>0)
                {
                    d.add(data_wmic);
                }
            }
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void getRegedit() 
	{
		try 
		{
			String filepath=System.getProperty("user.dir")+"\\db\\soft.reg";
            InputStream in=null;
            InputStreamReader ir=null;
            BufferedReader br=null;
            ArrayList<String> regedit=new ArrayList<String>();
            File file=new File(filepath);
            if(file.exists())
            {
                file.delete();
            }
            Process process=Runtime.getRuntime().exec("cmd /k reg export \"HKLM\\Software\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\" "+filepath);
            Thread.sleep(3000);
            in=new BufferedInputStream(new FileInputStream(file));
            ir=new InputStreamReader(in,"UTF-16LE");
            br=new BufferedReader(ir);
            String line="";
            String tmp="";
            while((line=br.readLine())!=null)
            {
                tmp=tmp+line;
            }
            Pattern pattern=Pattern.compile("\"DisplayName\"=\"(.*?)\"");
            Matcher matcher=pattern.matcher(tmp);
            while(matcher.find())
            {
                regedit.add(matcher.group().replace("=","").replace("\"","").replace("DisplayName",""));
            }

            for(int i=0;i<regedit.size();i++)
            {
                String[] data_regedit=new String[2];
                data_regedit[0]="Regedit";
                data_regedit[1]=regedit.get(i);
                if(data_regedit[1].length()>0) {
                    d.add(data_regedit);
                }
            }
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void getCPFx86() 
	{
		try 
		{
			 String x86doc="C:\\Program Files (x86)";
             ArrayList<String> x86=new ArrayList<String>();
             File file=new File(x86doc);
             File[] fs=file.listFiles();
             for(File f:fs)
             {
                 if(f.isDirectory())
                 {
                     if(f.getName().equals("Common Files"))
                     {
                         File cf=new File(x86doc+"\\Common Files");
                         File[] cfs=cf.listFiles();
                         for(File ff:cfs)
                         {
                             if(ff.isDirectory())
                             {
                                 x86.add(ff.getName());
                             }
                         }
                     }
                     else
                         {
                             String notin=f.getName();
                             switch (notin)
                             {
                                 case "InstallShield Installation Information":
                                     break;
                                 case "Microsoft Analysis Services":
                                     break;
                                 case "Microsoft SDKs":
                                     break;
                                 case "Microsoft.NET":
                                     break;
                                 case "MSBuild":
                                     break;
                                 case "Reference Assemblies":
                                     break;
                                 case "Windows Defender":
                                     break;
                                 case "Windows Mail":
                                     break;
                                 case "Windows Media Player":
                                     break;
                                 case "Windows Multimedia Platform":
                                     break;
                                 case "Windows NT":
                                     break;
                                 case "Windows Photo Viewer":
                                     break;
                                 case "Windows Portable Devices":
                                     break;
                                 case "WindowsPowerShell":
                                     break;
                                 default:
                                       x86.add(f.getName());
                                     break;
                             }
                         }
                 }
             }

             for(int i=0;i<x86.size();i++)
             {
                 String[] data_x86=new String[2];
                 data_x86[0]="ProgramFiles_x86";
                 data_x86[1]=x86.get(i);
                 if(data_x86[1].length()>0)
                 {
                	 d.add(data_x86);
                 }

         }
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void getCPF() 
	{
		String doc="C:\\Program Files";
        ArrayList<String> programfiles=new ArrayList<String>();
        File file=new File(doc);
        File[] fs=file.listFiles();
        for(File f:fs)
        {
            if(f.isDirectory())
            {
                if(f.getName().equals("Common Files"))
                {
                    File cf=new File(doc+"\\Common Files");
                    File[] cfs=cf.listFiles();
                    for(File ff:cfs)
                    {
                        if(ff.isDirectory())
                        {
                            programfiles.add(ff.getName());
                        }
                    }
                }
                else
                    {
                        String notin=f.getName();
                        switch (notin)
                        {
                            case "Microsoft Analysis Services":
                                break;
                            case "ModifiableWindowsApps":
                                break;
                            case "MSBuild":
                                break;
                            case "Uninstall Information":
                                break;
                            case "UNP":
                                break;
                            case "Windows Defender":
                                break;
                            case "Windows Defender Advanced Threat Protection":
                                break;
                            case "Windows Mail":
                                break;
                            case "Windows Media Player":
                                break;
                            case "Windows Multimedia Platform":
                                break;
                            case "Windows NT":
                                break;
                            case "Windows Photo Viewer":
                                break;
                            case "Windows Portable Devices":
                                break;
                            case "Windows Security":
                                break;
                            case "WindowsApps":
                                break;
                            case "WindowsPowerShell":
                                break;
                            default:
                                programfiles.add(f.getName());
                                break;
                        }
                    }
            }
        }

        for(int i=0;i<programfiles.size();i++)
        {
            String[] data_programfiles=new String[2];
            data_programfiles[0]="ProgramFiles";
            data_programfiles[1]=programfiles.get(i);
            if(data_programfiles[1].length()>0)
            {
                d.add(data_programfiles);
            }
        }
	}
	
	public void getOthers() 
	{
		 ArrayList<String> others=new ArrayList<String>();
         File[] roots=File.listRoots();
         for(File f:roots)
         {
             String r=f.getAbsolutePath();
             if(!r.equals("C:\\"))
             {
                 r=r+"Program Files";
                 File t=new File(r);
                 if(t.exists())
                 {
                     File[] ff=t.listFiles();
                     for(File fff:ff)
                     {
                         if(fff.isDirectory())
                         {
                             others.add(fff.getName());
                         }
                     }
                 }
             }

         }

         for(int i=0;i<others.size();i++)
         {
             String[]data_others=new String[2];
             data_others[0]="Others";
             data_others[1]=others.get(i);
             if(data_others[1].length()>0)
             {
                 d.add(data_others);
             }
         }
	}
	
	
}
