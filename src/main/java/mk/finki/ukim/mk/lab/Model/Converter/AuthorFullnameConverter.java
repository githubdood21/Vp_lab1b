package mk.finki.ukim.mk.lab.Model.Converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mk.finki.ukim.mk.lab.Model.AuthorFullname;

@Converter
public class AuthorFullnameConverter implements AttributeConverter<AuthorFullname, String> {

    private static final String SEPERATOR = ", ";

    @Override
    public String convertToDatabaseColumn(AuthorFullname authorFullname) {
        if(authorFullname == null)
        {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if(authorFullname.getSurname() != null && !authorFullname.getSurname().isEmpty())
        {
            sb.append(authorFullname.getSurname());
            sb.append(SEPERATOR);
        }

        if(authorFullname.getName() != null && !authorFullname.getName().isEmpty())
        {
            sb.append(authorFullname.getName());
        }

        return sb.toString();
    }

    @Override
    public AuthorFullname convertToEntityAttribute(String s) {
        if(s == null || s.isEmpty())
        {
            return null;
        }

        String[] parts = s.split(SEPERATOR);

        if(parts == null || parts.length == 0)
        {
            return null;
        }

        AuthorFullname fullname = new AuthorFullname();
        String first = !parts[0].isEmpty() ? parts[0] : null;
        if(s.contains(SEPERATOR))
        {
            fullname.setSurname(first);

            if(parts.length >= 2 && parts[1] != null && !parts[1].isEmpty())
            {
                fullname.setName(parts[1]);
            }
            else{
                fullname.setName(first);
            }
        }

        return fullname;
    }
}
