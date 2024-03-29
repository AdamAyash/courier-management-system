package bg.tu_varna.sit.couriermanagementsystem.controllers.validation.validationrules;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;

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
        if(!_validationValue.matches("[A-Z][a-z]*") || _validationValue.isBlank())
        {
            MessageBox.information(Messages.INVALID_NAME__MESSAGE);
            return false;
        }
        return true;
    }

}
