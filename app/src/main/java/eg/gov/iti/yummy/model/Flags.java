package eg.gov.iti.yummy.model;

public class Flags {
    public static String getFlag (String name){
        if(name.equals("American"))return "https://countryflagsapi.com/png/America";
        else if(name.equals("British"))return "https://countryflagsapi.com/png/gb";
        else if(name.equals("Canadian"))return "https://countryflagsapi.com/png/Canada";
        else if(name.equals("Chinese"))return "https://countryflagsapi.com/png/China";
        else if(name.equals("Croatian"))return "https://countryflagsapi.com/png/croatia";
        else if(name.equals("Dutch"))return "https://countryflagsapi.com/png/netherlands";
        else if(name.equals("Egyptian"))return "https://countryflagsapi.com/png/Egypt";
        else if(name.equals("French"))return "https://countryflagsapi.com/png/France";
        else if(name.equals("Greek"))return "https://countryflagsapi.com/png/Greece";
        else if(name.equals("Indian"))return "https://countryflagsapi.com/png/India";
        else if(name.equals("Irish"))return "https://countryflagsapi.com/png/ireland";
        else if(name.equals("Italian"))return "https://countryflagsapi.com/png/Italy";
        else if(name.equals("Jamaican"))return "https://countryflagsapi.com/png/jamaica";
        else if(name.equals("Japanese"))return "https://countryflagsapi.com/png/Japan";
        else if(name.equals("Kenyan"))return "https://countryflagsapi.com/png/Kenya";
        else if(name.equals("Malaysian"))return "https://countryflagsapi.com/png/Malaysia";
        else if(name.equals("Mexican"))return "https://countryflagsapi.com/png/Mexico";
        else if(name.equals("Moroccan"))return "https://countryflagsapi.com/png/Morocco";
        else if(name.equals("Polish"))return "https://countryflagsapi.com/png/Poland";
        else if(name.equals("Portuguese"))return "https://countryflagsapi.com/png/portugal";
        else if(name.equals("Russian"))return "https://countryflagsapi.com/png/Russia";
        else if(name.equals("Spanish"))return "https://countryflagsapi.com/png/Spain";
        else if(name.equals("Thai"))return "https://countryflagsapi.com/png/thailand";
        else if(name.equals("Tunisian"))return "https://countryflagsapi.com/png/tunisia";
        else if(name.equals("Turkish"))return "https://countryflagsapi.com/png/turkey";
        else if(name.equals("Vietnamese"))return "https://countryflagsapi.com/png/vietnam";
        else return "https://countryflagsapi.com/png/mayotte";
    }
}
