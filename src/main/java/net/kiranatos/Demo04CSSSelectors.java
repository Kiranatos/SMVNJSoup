package net.kiranatos;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Demo04CSSSelectors {
    private final static String path = "/html/Demo04.html";
    
    public static void main(String[] args) {
        System.out.println("Element.select(String selector)\n" 
                         + "Elements.select(String selector)\n");
        String htmlString = ProjectHelper.getHTMLStringFromFile(path);
        Document docString = Jsoup.parse(htmlString);
        //String cssSelectors = document.cssSelector();        
        //Element e2 = document.closest("");
        
        // a with href
        Elements e_links = docString.select("a[href]");
        for (Element el: e_links) System.out.println("1.) a[href] = \t" + el);
        
        // img with src ending .png
        Elements e_pngs = docString.select("img[src$=.png]");
        for (Element el: e_pngs) System.out.println("2.) img[src$=.png] = \t" + el);
        
        // div with class=masthead
        Element e_class = docString.select("div.masthead").first();
        System.out.println("3.) div.masthead = \t" + e_class);
        
        // тег <a>, который внутри тега <h3 class="romeo">
        Elements e_resultLinks = docString.select("h3.romeo > a");
        for (Element el: e_resultLinks) System.out.println("4.) h3.romeo > a = \t" + el);
        
        //  Query <a> elements, href contain /document/
        e_resultLinks = docString.select("a[href*=/document/]");
        for (Element el: e_resultLinks) System.out.println("5.) a[href*=/document/] = \t" + el);
        
        // First <div> element has class ="related-container"
        Element e_div = docString.select("div.related-container").first();
        System.out.println("6.) .select(div.ИмяКласса).first() = \t" + e_div);
 
        // List the <h3>, the direct child elements of the current element.
        Elements h3Elements = e_div.select("> h3");
        for (Element el: h3Elements) System.out.println("7.) .select(> h3) = \t" + el);
 
        // Get first <h3> element
        Element h3 = h3Elements.first();
        System.out.println("7.1.) .first() = \t" + h3); 
        System.out.println("7.2.) .text() = \t" + h3.text());
 
        // List <a> elements, is a descendant of the current element
        Elements _aElements = e_div.select("a");
        System.out.println("8.) Количество <a> тегов внутри 1-го div: " + _aElements.size());
        
        // тег <img> внутри тега <h4 id="myId"> // т.ч. можно задавать .select("tag#id.class tag tag.class.class tag");
        Elements _imgInH4 = docString.select("h4#myId img");
        for (Element _el: _imgInH4) System.out.println("9.) h4#myId img = \t" + _el.attr("src"));
    }    
}

/* https://o7planning.org/ru/10399/jsoup-java-html-parser-tutorial
                        Обзор Selector (Селектор).    
tagname                                         Искать элементы по тегу. Например: a
ns|tag                                          Искать элементы по тегу в пространстве имент (namespace), например fb|name значит найти элементы <fb:name>
#id                                             Искать элементы по ID, например #logo
.class:                                         Искать элементы по названию класса, например .masthead
[attribute]                                     Элементы с атрибутами, например [href]
[^attr]                                         Элементы с атрибутами с приставкой, например [^data-] искать элементы с атрибутами начинающимися на data-
[attr=value]                                    Элементы со значениями атрибута, например [width=500] (Можно использовать ковычки)
[attr^=value], [attr$=value], [attr*=value]     Элементы со значениями атрибута, начинающиеся, заканчивающиеся, или содержащие значение, например [href*=/path/]
[attr~=regex]                                   Элементы со значениями совпадающими с регулярным выражением, например img[src~=(?i)\.(png|jpe?g)]
*                                               Все элементы, например *
                        Комбинации Selector 
el#id                                           Элементы с ID, например div#logo
el.class                                        Элементы с классом, например div.masthead
el[attr]                                        Элементы с атрибутом, например a[href] или a[href].highlight
ancestor child                                  (Родительский элемент - наследованный элемент) Подэлементы родительского элемента, например . .body p ищет элемент p везде под блоком с классом "body"
parent > child                                  Прямые элементы наследники родительского элемента, например div.content > p найти элементы p которые являются прямыми наследниками div имеющие class ='content'; и body > * найти прямые подэлементы тега body
siblingA + siblingB                             Найти элементы братья B сразу перед элементом A, например div.head + div
siblingA ~ siblingX                             Найти элементы братья X перед элементом A, например h1 ~ p
el, el, el                                      Группа с разными Selector, ищет элементы подходящие к одному из Selector; например div.masthead, div.logo
                        Pseudo selectors    
:lt(n)                                          Поиск элементов с родственным индексом (местоположение в дереве DOM связь с родтельским элементом) меньше n; например td:lt(3)
:gt(n)                                          Поиск элементов с родственным индексом больше n, например div p:gt(2)
:eq(n)                                          Поиск элементов с родственным индексом равным n; e.g. form input:eq(1)
:has(seletor)                                   Поиск элементов содержащих элементы совпадающие с selector; например div:has(p)
:not(selector)                                  Поиск элементов несовпадающих с Selector; например div:not(.logo)
:contains(text)                                 Поиск элементов содержащих данный текст. Поиск не отличая заглавные или строчные буквы, например p:contains(jsoup)
:containsOwn(text)                              Поиск элементов которые напрямую содержат данный текст
:matches(regex)                                 Поиск элементов где текст не совпадает с определенным обычным выражением; например div:matches((?i)login)
:matchesOwn(regex)                              Поиск элементов где текст совпадает с определенным обычным выражением.
            Примечение: Способ индекса pseudo начинается с 0, первый элемент имеет индекс 0, второй элемент имеет индекс 1,..

*/