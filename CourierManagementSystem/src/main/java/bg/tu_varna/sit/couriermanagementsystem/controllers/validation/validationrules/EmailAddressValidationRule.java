package bg.tu_varna.sit.couriermanagementsystem.controllers.validation.validationrules;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;

public class EmailAddressValidationRule extends ValidationRule<String>
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

    public EmailAddressValidationRule(String validationValue)
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
        if(!_validationValue.matches("^.*@.*mail.*$") || _validationValue.isBlank())
        {
            MessageBox.information(Messages.INVALID_EMAIL_ADDRESS_MESSAGE);
            return false;
        }
        return true;
    }
}
