package bg.tu_varna.sit.couriermanagementsystem.controllers.validation.validationrules;

import bg.tu_varna.sit.couriermanagementsystem.common.MessageBox;
import bg.tu_varna.sit.couriermanagementsystem.common.messages.Messages;

public class UCNValidationRule extends ValidationRule<String>
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
    public UCNValidationRule(String validationValue)
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
        _validationValue.trim();
        if(!_validationValue.matches("^[0-9]{10}$") || _validationValue.isBlank())
        {
            MessageBox.information(Messages.INVALID_UCN_EGFN_MESSAGE);
            return false;
        }
        return true;
    }
}
