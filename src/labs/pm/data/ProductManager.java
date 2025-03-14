/*
 *
 * Copyright (c) 2023 Oracle and/or its affiliates.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or data
 * (collectively the "Software"), free of charge and under any and all copyright
 * rights in the Software, and any and all patent rights owned or freely
 * licensable by each licensor hereunder covering either (i) the unmodified
 * Software as contributed to or provided by such licensor, or (ii) the Larger
 * Works (as defined below), to deal in both
 *
 * (a) the Software, and
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software (each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 * The above copyright notice and either this complete permission notice or at
 * a minimum a reference to the UPL must be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.lang.StringBuilder;
import labs.pm.data.Reteable;
/**
 * @author Stika
 **/
public class ProductManager {

    private Map <Product,List<Review>> products  =new HashMap<>();

    private Locale locale ;
    private ResourceBundle resources ;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat ;


    public ProductManager(Locale locale) {
        this.locale = locale;
        resources = ResourceBundle.getBundle("labs.pm.data.ressources",locale);
        dateFormat =DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat= NumberFormat.getCurrencyInstance(locale);
    }

    public  Product CreateProduct(int id , String name , BigDecimal price , Rating rating, LocalDate bestBefore){
        Product p =new Food(id,name,price,rating,bestBefore);
        products.putIfAbsent(p,new ArrayList<>());
        return p;
    }

    public  Product CreateProduct(int id , String name , BigDecimal price , Rating rating){
        Product p=new Drink(id,name,price,rating);
        products.putIfAbsent(p,new ArrayList<>());
        return p ;
    }

    public Product reviewProduct(Product p , Rating r , String c ){
        List <Review> reviews  = products.get(p);
        reviews.add(new Review(r,c));
        products.remove(p);
        int sum = 0 ;
        for (Review rev : reviews) {
            sum+=rev.rate().ordinal();
        }
        p.setRating(Reteable.convert((int)(sum/reviews.size())));
        products.put(p,reviews);
        return p;
    }
    public Product reviewProduct(int id, Rating r , String c){
        return reviewProduct(findProduct(id),r,c);
    }
    public void printProductReport(Product p ){
        StringBuilder txt =  new StringBuilder();
        List <Review> reviews = products.get(p);
        String type  = switch (p) {
            case Food food -> resources.getString("food") ;
            case Drink drink -> resources.getString("drink");
            case null -> "none";
        };
        txt.append(MessageFormat.format(resources.getString("product"),p.getName(),moneyFormat.format(p.getPrice()),p.getRating().getStars(),dateFormat.format(p.getBestBefore()),type));
        txt.append("\n");
        Collections.sort(reviews);
        for (Review r:reviews){
                if (r == null ) break ;
                txt.append(MessageFormat.format(resources.getString("review"),r.rate().getStars(),r.Comments()));
                txt.append("\n");
        }
        if (reviews.isEmpty()) {
            txt.append(resources.getString("no.reviews"));
            txt.append("\n");


        }
        System.out.println(txt);
    }
    public void printProductReport(int id) {
        printProductReport(findProduct(id));
    }


    public Product findProduct(int id){
        for ( Product p : this.products.keySet()) {
            if(p.getId() == id ) return p ;
        }
        return null;
    }

}
