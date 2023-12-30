package bg.tu_varna.sit.couriermanagementsystem.controllers.validation.validationrules;


/**/
public abstract class ValidationRule<ValidationType>
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private ValidationType _validationValue;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public ValidationRule(ValidationType validationValue)
    {
        _validationValue = validationValue;
    }

    //-------------------------
    //Methods:
    //-------------------------
    public abstract boolean validate();

    //-------------------------
    //Overrides:
    //-------------------------

}
