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

package labs.pm.app;

import labs.pm.data.*;

import javax.sound.midi.SysexMessage;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.PrimitiveIterator;

/**
 * (@code Shop) class represents an application that manages Products
 * @version 4.0
 * @author Stika
 **/

public class Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager(Locale.UK);


          Product p6 = pm.CreateProduct(104,"Chocolate",BigDecimal.valueOf(2.99),Rating.FIVE_STAR);
          Product p7 = pm.CreateProduct(104,"Chocolate",BigDecimal.valueOf(2.99),Rating.FIVE_STAR,LocalDate.now().plusDays(2));

          Product p1 = pm.CreateProduct(101,"Tea",BigDecimal.valueOf(1.99),Rating.THREE_STAR);
          Product p2 = pm.CreateProduct(102,"Coffe",BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
          Product p3 = pm.CreateProduct(103,"Cake",BigDecimal.valueOf(3.99),Rating.FIVE_STAR, LocalDate.now().plusDays(2));
          Product p4 = pm.CreateProduct(105,"Cookie",BigDecimal.valueOf(3.99),Rating.TWO_STAR,LocalDate.now());





        p4= pm.reviewProduct(105,Rating.FOUR_STAR,"Very Very Very good :=)");
        p4=pm.reviewProduct(105,Rating.FOUR_STAR,"Very Very Very good :=)");
        p4=pm.reviewProduct(105,Rating.FOUR_STAR,"Very Very Very good :=)");
        p4=pm.reviewProduct(103,Rating.THREE_STAR,"Very Very Very good :=)");

        p4=pm.reviewProduct(103,Rating.ONE_STAR,"Very Very Very good :=)");
        p4=pm.reviewProduct(105,Rating.FOUR_STAR,"Very Very Very good :=)");



        pm.printProductReport(p3);
        pm.printproducts((p11,p22) -> p11.getRating().ordinal() - p22.getRating().ordinal());
        System.out.println("---------------------------------------------");
        pm.printproducts((p11,p22) -> p22.getRating().ordinal() - p11.getRating().ordinal());




//        System.out.println("Product :"+p2.getId()+"\nName :"+p2.getName()+"\nPrice :"+p2.getPrice()+"\nPrice after discount :"+p2.getDiscountPrice()+"\nRating : "+p2.getRating().getStars());
//        System.out.println("Product :"+p3.getId()+"\nName :"+p3.getName()+"\nPrice :"+p3.getPrice()+"\nPrice after discount :"+p3.getDiscountPrice()+"\nRating : "+p3.getRating().getStars());
//        System.out.println("Product :"+p4.getId()+"\nName :"+p4.getName()+"\nPrice :"+p4.getPrice()+"\nPrice after discount :"+p4.getDiscountPrice()+"\nRating : "+p4.getRating().getStars());
//        System.out.println("Product :"+p5.getId()+"\nName :"+p5.getName()+"\nPrice :"+p5.getPrice()+"\nPrice after discount :"+p5.getDiscountPrice()+"\nRating : "+p5.getRating().getStars());

    }
}