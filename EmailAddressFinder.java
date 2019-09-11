//Name: Mohammed Khalid Jamal
//ID:1978876


//package com.bham.pij.assignments.a1a;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class EmailAddressFinder 
{
    
    private static ArrayList<String> emailAddresses;
    
    public static void main(String[] args) {
        emailAddresses = new ArrayList<String>();
        EmailAddressFinder eaf = new EmailAddressFinder();
        eaf.run();
        System.out.println("Email addresses found: " + emailAddresses.size());
    }
    
    public void run() {
        
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader("corrupteddb"));
     
            String input = "";
           
            PrintWriter pw = new PrintWriter("eaf");
            
            while ((input = reader.readLine()) != null) {
                
                input = input.trim();
                
                ArrayList<String> temp = new ArrayList<String>();
                
                temp = findEmailAddresses(input);             
                
                for (String t: temp) {
                    emailAddresses.add(t);
                }                
            }
            
            pw.close();
            reader.close();
        }
        
        catch(IOException e) {
            e.printStackTrace();
        }        
    }
    
    public boolean checklocal(String input)
    {

        if(input.length()<1)
        return false;

        int scount=0;

       for(int i=0;i<input.length();i++)
       {
            if(Character.isLetter(input.charAt(i))==false && Character.isDigit(input.charAt(i))==false && input.charAt(i)!='.' && input.charAt(i)!='_')
                {
                    input=input.replace(input.substring(0,i+1),"");  
                    
                    if(input.length()<1)
                    return false;
                    
                    i=-1;
                       
                }
        }
        if(input.charAt(0)=='.')
        input=input.substring(1);

        if(input.charAt(input.length()-1)=='.')
        return false;
        
            for(int l=0;l<input.length();l++)
            {
                 if(Character.isDigit(input.charAt(l))==false && Character.isAlphabetic(input.charAt(l))==false && input.charAt(l)!='.' && input.charAt(l)!='_')
                 return false;

                 else if(input.charAt(l)=='.')
                 scount++;

            }
        for(int i=1;i<scount;i++) 
        {
         input=input.replace(input.substring(0,input.indexOf('.')+1),"");
        }

         if(scount<=1)
         return true;

         else
         return false;

        }
    


    public boolean checkdomain(String input)
    {    
        
        int scount=0;
        if(input.charAt(0)=='.')
        return false;

        for(int i=0;i<input.length();i++)
        {if(input.charAt(i)=='.');
        scount++;
        }
        int firsts=input.indexOf('.')+1;
        if(scount==1)
        {
            String tld = input.substring(input.indexOf('.')+1);
            if(checktld(tld)==true)
          {  
            for(int i=0;i<input.indexOf('.');i++)
            {
                if(Character.isLowerCase(input.charAt(i)) == false && input.charAt(i)!='.')
                return false;
            }
            return true; 
          }   

        }
       
        if(scount>1)
        {
            int seconds=input.indexOf('.',firsts)+1;
            
            String tld1 = input.substring(firsts);
            String tld2 = input.substring(seconds);
            if(checktld(tld1)==true)
            {  
    
            for(int i=0;i<firsts;i++)
            {
                if(Character.isLowerCase(input.charAt(i)) == false && input.charAt(i)!='.')
                return false;
            }
            return true;
            }
            if(checktld(tld2)==true)
            {
                for(int i=0;i<seconds;i++)
                {
                    if(Character.isLowerCase(input.charAt(i)) == false && input.charAt(i)!='.')
                    return false;

                }
                return true;
            }
        }
        return false;
    }

    public boolean checktld(String input)
    {
        String[] TopLevelDomain = {"net","com","uk","de","jp","ro"};

        if(input.length()<2)
            return false;

        else
        {
            for(int i=0;i<TopLevelDomain.length;i++)
            {
                if(input.substring(0,2).equals(TopLevelDomain[i]))
                { 
                  return true;
                }
            }
            for(int i=0;i<TopLevelDomain.length;i++)
            {
               if(input.length()<3)
               return false;
               
               else if(input.substring(0,3).equals(TopLevelDomain[i]))
                {
                return true;
                }
            }
        }
        return false;
    }
    


   public String trimemail(String input)
    {
        
        int scount=0;

        for(int j=0;j<input.lastIndexOf('@');j++)
        {
            if(Character.isLetter(input.charAt(j))==false && Character.isDigit(input.charAt(j))==false && input.charAt(j)!='.' && input.charAt(j)!='_' && input.charAt(j)!='@')
            {
                input=input.replace(input.substring(0,j+1),"");  
                j=-1;   
            }
         }
         for(int i=0;i<input.lastIndexOf('@');i++)
          {if(input.charAt(i)=='.')
            scount++;
          }
          for(int i=1;i<scount;i++)
              input=input.replace(input.substring(0,input.indexOf('.')+1),"");

        if(input.charAt(0)=='.')
        input=input.substring(1);

              String[] email=input.split("@");
              if(email.length>1)
              {String newdomain = trimdomain(email[1]);
              if(newdomain!=null)
              {String newemail=email[0].concat("@").concat(newdomain);
              return newemail;
              }
              }
            
              return null;
    }

        public String trimdomain(String input)
        {
            String[] TopLevelDomain = {"net","com","uk","de","jp","ro"};
          
          if(input.indexOf('.')==-1 )
          return null;

          int firsts=input.indexOf('.')+1;
          int seconds=input.indexOf('.',firsts)+1;
              
              String domain1=input.substring(firsts);
              String domain2=input.substring(seconds);
            if(domain1.length()>1)
           {
                for(int i=0;i<TopLevelDomain.length;i++)
            {
                String tld1=input.substring(firsts,firsts+2);

                if(tld1.equals(TopLevelDomain[i]))
                { 
                   input=input.substring(0,firsts+2);
                   return input;
                }
                if(domain1.length()>2)
                {
                  String tld2=input.substring(firsts,firsts+3);
                 if(tld2.equals(TopLevelDomain[i]))
                 {
                    input=input.substring(0,firsts+3);
                    return input;
                 }
                }
            }
           }
           if(domain2.length()>1)
           {
                for(int i=0;i<TopLevelDomain.length;i++)
            {
                String tld1=input.substring(seconds,seconds+2);

                if(tld1.equals(TopLevelDomain[i]))
                { 
                   input=input.substring(0,seconds+2);
                   return input;
                }
                if(domain2.length()>2)
                {
                  String tld2=input.substring(seconds,seconds+3);
                 if(tld2.equals(TopLevelDomain[i]))
                 {
                    input=input.substring(0,seconds+3);
                    return input;
                 }
                }
            }
           }

            return null;
    }



    public ArrayList<String> findEmailAddresses(String input) {
        
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> list4 = new ArrayList<String>();    
        ArrayList<String> list5 = new ArrayList<String>();

         String[] list2 = input.split(" ");
         for(String s:list2)
         list4.add(s);
 
         for(int i=0;i<list4.size();i++)
        {
            int atcount=0;

            for(int j=0;j<list4.get(i).length();j++)
            {
            if(list4.get(i).charAt(j)=='@')
            atcount++;
            }

            if(atcount==1)
            {
                String[] list3=list4.get(i).split("@");

                if(list3.length>1)
               {
                boolean local = checklocal(list3[0]);
                boolean domain = checkdomain(list3[1]);

                if(local==true && domain==true)
                {
                String email = trimemail(list4.get(i));
                if(email!=null)
                list.add(email);
                }
               }
            }

            else if(atcount>1)
            {   
                String[] TopLevelDomain = {"net","com","uk","de","jp","ro"};
                
                for(int j=0;j<list4.get(i).length();j++)
        out:    {
            if(list4.get(i).charAt(0)=='@')
            {String s=list4.get(i).substring(1);
            list4.set(i,s); 
            break out;     
            }
            
            if(Character.isDigit(list4.get(i).charAt(j))==false && Character.isLetter(list4.get(i).charAt(j))==false && list4.get(i).charAt(j)!='.' && list4.get(i).charAt(j)!='_' && list4.get(i).charAt(j)!='@')
                        {
                            String s=list4.get(i).replace(list4.get(i).substring(0,j+1),"");  
                            list4.set(i,s);
                            j=-1;
                            break out;
                        }
                    else if(list4.get(i).charAt(j)=='@')
                    {
                        for(int k=j+1;k<list4.get(i).length();k++)
                     {
                         if(list4.get(i).charAt(k)=='@')
                        {
                            String s=list4.get(i).replace(list4.get(i).substring(0,j+1),"");
                            list4.set(i,s); 
                           
                           j=-1;
                           break out; 
        
                        }
                    else if(Character.isDigit(list4.get(i).charAt(k))==false && Character.isAlphabetic(list4.get(i).charAt(k))==false && list4.get(i).charAt(k)!='.' && list4.get(i).charAt(k)!='_')
                        {
                        String s=list4.get(i).replace(list4.get(i).substring(0,k+1),"");
                        list4.set(i,s);
                        j=-1;
                        }
                        
                        else if(list4.get(i).charAt(k)=='.')
                      {
                            
                       for(int m=j+1;m<k;m++)
                        {
                            if(Character.isLowerCase(list4.get(i).charAt(m)) == false && list4.get(i).charAt(m)!='.')
                            {String s=list4.get(i).replace(list4.get(i).substring(0,j+1),"");
                            list4.set(i,s);
                           break out; 
                        }

                        }
                        String tld=list4.get(i).substring(k+1);
                            
                            for(int l=0;l<TopLevelDomain.length;l++)
                        {
                            if(tld.length()>1)
                          {
                            if(list4.get(i).substring(k+1,k+3).equals(TopLevelDomain[l]))
                            { 
                                String email=trimemail(list4.get(i).substring(0,k+3));
                                if(email!=null)
                                list.add(email);
                                String s=list4.get(i).replace(list4.get(i).substring(0,k+3),"");
                                list4.set(i,s);
                                
                                j=-1;
                                break out; 
                            }
                          }
                          if(tld.length()>2)
                         {
                            if(list4.get(i).substring(k+1,k+4).equals(TopLevelDomain[l]))
                            {
                             
                                String email=trimemail(list4.get(i).substring(0,k+4));
                                if(email!=null)
                                list.add(email);
                               String s=list4.get(i).replace(list4.get(i).substring(0,k+4),"");
                               list4.set(i,s); 
                               
                               j=-1;
                               break out;

                            }
                         }
                        }
                         
                      }
                      

                     }
                    }
                   
              }
            }
        }
        for(int i=0;i<list5.size();i++)
        {
            String s=trimemail(list5.get(i));
            if(s!=null)
            list.add(s);
        }
        
        return list;
    }
}
