package bg.tu_varna.sit.couriermanagementsystem.controllers.validation.validationrules;

public class NameValidationRule extends ValidationRule<String>
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public NameValidationRule(String validationValue)
    {
        super(validationValue);
    }

    //-------------------------
    //Methods:
    //-------------------------

    //-------------------------
    //Overrides:
    //-------------------------
    @Override
    public boolean validate()
    {
        return false;
    }

}
