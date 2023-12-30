package bg.tu_varna.sit.couriermanagementsystem.controllers.validation;

import bg.tu_varna.sit.couriermanagementsystem.controllers.validation.validationrules.ValidationRule;

import java.util.ArrayList;
import java.util.List;

/**/
public class Validator
{
    //-------------------------
    //Constants:
    //-------------------------

    //-------------------------
    //Members:
    //-------------------------
    private List<ValidationRule> _validationRulesList;

    //-------------------------
    //Properties:
    //-------------------------

    //-------------------------
    //Constructor/Destructor:
    //-------------------------
    public Validator()
    {
        _validationRulesList = new ArrayList<>();
    }

    //-------------------------
    //Methods:
    //-------------------------
    public boolean validate()
    {
        for(ValidationRule rule : _validationRulesList)
        {
            if(!rule.validate())
                return false;
        }
        return true;
    }

    //-------------------------
    //Overrides:
    //-------------------------
}
