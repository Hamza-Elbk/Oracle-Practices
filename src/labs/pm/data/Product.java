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
import java.time.LocalDate;
import java.util.Objects;

import static java.math.RoundingMode.HALF_UP;
import static labs.pm.data.Rating.*;

/**
 * (@code Product) Class that represent Product
 * <br>
 * Each product can have a discount  calculated based on a
 * {@link DISCOUNT_RATE discount rate}
 * @version 4.0
 * @author Stika
 **/
public sealed abstract class   Product  implements Reteable<Product> permits Food , Drink{
    private int id ;
    private String name;
    private BigDecimal price;
    public static final BigDecimal DISCOUNT_RATE=BigDecimal.valueOf(0.1);

    private Rating rating;

    protected Product(int id,String name,BigDecimal price,Rating rating){
        this.id=id;
        this.name=name;
        this.price=price;
        this.rating=rating;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating.getStars() +
                '}';
    }

    public Rating getRating() {
        return rating;
    }

    /**
     * Assumes that the best before date is today
     * @return the current date
     */
    public LocalDate getBestBefore(){return LocalDate.now();};

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public abstract Product applyRating(Rating rating) ;
    public BigDecimal getPrice() {
        return price;
    }


    public static BigDecimal getDiscountRate(){
        return DISCOUNT_RATE;
    }

    /**
     * Calculates Discount price base on
     * {@link price } and {
     * @link DISCOUNT_RATE}
     * @return a {@link java.math.BigDecimal BigDecimale} value of discount
     */
    public BigDecimal getDiscountPrice(){
        return price.multiply(DISCOUNT_RATE).setScale(2,HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Product product)
        return id == product.id ;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
