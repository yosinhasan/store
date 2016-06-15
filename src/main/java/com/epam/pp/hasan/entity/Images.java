package com.epam.pp.hasan.entity;

/**
 * @author Yosin_Hasan
 */
public class Images extends Entity {
    private Integer productId;
    private String image;

    public final Integer getProductId() {
        return productId;
    }

    public final void setProductId(Integer productId) {
        this.productId = productId;
    }

    public final String getImage() {
        return image;
    }

    public final void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Images [productId=" + productId + ", image=" + image + ", id=" + id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
        Images other = (Images) obj;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        return true;
    }
}
