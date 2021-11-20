package net.kiranatos;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Demo03ElementsMethods {
    private final static String path = "/html/Demo02and03.html";
    private static Document document = Jsoup.parse(ProjectHelper.getHTMLStringFromFile(path));
    private static int index = 1;
    
    public static void main(String[] args) {
        System.out.println("Selectors: \n\tElement.select(String selector) \n\tElements.select(String selector)\n\n\t");
        
        System.out.printf("\tExample %d) Select by tag%n", index++);
        Elements elems = document.select("img");
        //elems = document.getElementsByTag("div");
        ProjectHelper.printElements(elems);
        
        OzoHelper.lineSeparator(70, 3, '*');        
        
        System.out.printf("\tExample %d) Select by class%n", index++);
        elems = document.select(".classC1"); // <h2 class="classC1">
        ProjectHelper.printElements(elems);
        
        OzoHelper.lineSeparator(70, 3, '*');
        
        System.out.printf("\tExample %d) Select by id%n", index++);
        elems = document.select("#exC55"); // <h2 class="classC1">
        ProjectHelper.printElements(elems);
        
        OzoHelper.lineSeparator(70, 3, '*');
        
        System.out.printf("\tExample %d) Select by tag and class%n", index++);
        elems = document.select("h2.classC1"); // <h2 class="classC1">
        ProjectHelper.printElements(elems);
        
        OzoHelper.lineSeparator(70, 3, '*');
        
        System.out.printf("\tExample %d) Select by CSS-selectors%n", index++);
        elems = document.select("div#exD.myClassD2.myClassD3"); // <div id="exD" class="myClassD2 myClassD3">Text 74</div>
        ProjectHelper.printElements(elems);        
    }
}
