package com.epam.pp.hasan.entity;

/**
 * @author Yosin_Hasan
 */
public class Discount extends Entity {
    private Integer discount;

    public final Integer getDiscount() {
        return discount;
    }

    public final void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Discount other = (Discount) obj;
        if (discount == null) {
            if (other.discount != null)
                return false;
        } else if (!discount.equals(other.discount))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Discount [discount=" + discount + ", id=" + id + "]";
    }

}
