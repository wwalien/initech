You are working for a company called "Initech" on a web service with your coworker "Bill". 
Bill just sent you an email that the customer contact field on the company's website 
is saving personal customer information in the clear. 

The contact field on the website has the label "Email or Phone" with a character 
length of 80 and the data is not validated on the front end. Bill has said the email 
domain does not have to be masked, but the customer's email must be masked except 
for the first and last character. Bill said for phone numbers we need to mask the 
middle three digits and leave everything else in the clear. Bill says he wants a 
simple utility method he can pass this field and get a masked result back for the 
database. Bill also needs to see lots of test proof that this method works. 
Bill just left for a vacation to Brazil, so document any assumptions that you think Bill 
would agree with.



# Notes and Assumptions
1. Regarding your statement "website is saving personal customer information in the clear".
Initially, my thought was that you wanted this information encrypted in the database. If 
this is the case, please say so.  As this requires encrypting all existing contact fields,
this is an issue that should be resolved with all stake holders on board.
1. I've implemented a ContactMasker class with the static method maskContact(String) that 
accepts either an email address or a phone number. 
    1. Re: your comment about client not constraining the Contact to no more than 80 characters.
    If passed a string longer than 80 characters, _ContactMasker.maskContact()_  will throw an
    IllegalArgumentException() when it encounters a Contact value longer than 80 characters. 
    Consideration should be given to performing this check on the client side to prevent a round
    trip to the server.
    1. Given that email address is required to contain an @ symbol, and givent that phone number
    cannot contain an @ symbol, maskContact() identifies the contact type by testing for the 
    containing the @ symbol in the contact string.
1. I've implemented a very simple email address parser/validator. The only requirement is that
the email address be composed of two strings delimited by the @ symbol, e.g. _local@domain_.
It makes no attempt to validate the actual local or domain components. Furthermore, it makes
not attempt to determine if the mailbox actually exists. A more comprehensive solution should be
implemented.  A good place to start on those requirements would be [RFC-5322](https://tools.ietf.org/html/rfc5322#section-3.4) 
1. I've implemented a simple Phone Number parser/validator. The only requirement is that the
phone number contain exactly ten numeric characters. Any decorators in the number are ignored.
A more comprehensive solution should be used, e.g. [libphonenumber](https://github.com/google/libphonenumber).
1. Examples of usages and expected constraints can be found in the files ContactMasterTests.java,
EMailAddressTests.java and PhoneNumberTests.java    
