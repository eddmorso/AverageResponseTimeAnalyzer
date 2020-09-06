package Entities;

import Interfaces.QuestionConstants;

import java.util.Objects;

public class QuestionType extends Type {
    private int type;
    private int category;
    private int subcategory;
    private boolean anyCategory;
    private boolean anySubcategory;

    public QuestionType(int type, int category, int subcategory) {
        checkQuestionType(type);
        checkQuestionCategory(category);
        checkQuestionSubcategory(subcategory);

        this.type = type;
        this.category = category;
        this.subcategory = subcategory;
    }

    public QuestionType(int type, int category) {
        checkQuestionType(type);
        checkQuestionCategory(category);

        this.type = type;
        this.category = category;

        setAnySubcategory(true);
    }

    public QuestionType(int type) {
        checkQuestionType(type);

        this.type = type;

        setAnyCategory(true);
    }

    public QuestionType() {
        setAnyType(true);
    }

    private void checkQuestionType(int type) {
        if (!(type <= QuestionConstants.AMOUNT_OF_QUESTION_TYPES && type > 0))
            throw new RuntimeException("Wrong syntax");
    }

    private void checkQuestionCategory(int category) {
        if (!(category <= QuestionConstants.AMOUNT_OF_QUESTION_CATEGORIES && category > 0))
            throw new RuntimeException("Wrong syntax");
    }

    private void checkQuestionSubcategory(int subcategory) {
        if (!(subcategory <= QuestionConstants.AMOUNT_OF_QUESTION_SUBCATEGORIES && subcategory > 0))
            throw new RuntimeException("Wrong syntax");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionType questionType = (QuestionType) o;

        if (questionType.isAnyType() || isAnyType()) {
            return true;
        }
        if (questionType.isAnyCategory() || isAnyCategory()) {
            return type == questionType.type;
        }
        if (questionType.isAnySubcategory() || isAnySubcategory()) {
            return type == questionType.type && category == questionType.category;
        }
        return type == questionType.type &&
                category == questionType.category &&
                subcategory == questionType.subcategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, category, subcategory);
    }

    //Getters & Setters
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        setAnyType(false);
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
        setAnyType(false);
    }

    public int getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(int subcategory) {
        this.subcategory = subcategory;
        setAnyType(false);
    }

    public boolean isAnyCategory() {
        return anyCategory;
    }

    public void setAnyCategory(boolean anyCategory) {
        this.anyCategory = anyCategory;
    }

    public boolean isAnySubcategory() {
        return anySubcategory;
    }

    public void setAnySubcategory(boolean anySubcategory) {
        this.anySubcategory = anySubcategory;
    }
}
