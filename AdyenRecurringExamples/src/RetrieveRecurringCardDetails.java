

import java.util.Date;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

import com.adyen.services.payment.Recurring;
import com.adyen.services.recurring.RecurringDetail;
import com.adyen.services.recurring.RecurringDetailsRequest;
import com.adyen.services.recurring.RecurringDetailsResult;
import com.adyen.services.recurring.RecurringLocator;
import com.adyen.services.recurring.RecurringPortType;

public class RetrieveRecurringCardDetails {

	/**
	 * @param args
	 */
	public static void main ( String[] args ) {
		try {
			
			RecurringPortType ws = new RecurringLocator().getRecurringHttpPort( new java.net.URL( Credentials.recurring_test_address) );
			
			//Basic HTTP Authentication:
			( (Stub) ws )._setProperty( Call.USERNAME_PROPERTY, Credentials.ws_user );
			( (Stub) ws )._setProperty( Call.PASSWORD_PROPERTY, Credentials.pwd_user );
			
			RecurringDetailsRequest request = new RecurringDetailsRequest();
			request.setMerchantAccount( Credentials.merchant_account );
			request.setRecurring( new Recurring( "RECURRING", null) );
			request.setShopperReference( "shopper0001" );
			
			RecurringDetailsResult result =  ws.listRecurringDetails( request );
			System.out.println( new Date() );
			
			System.out.println( result.getLastKnownShopperEmail() );
			System.out.println( result.getShopperReference());
			System.out.println( result.getCreationDate() );
			RecurringDetail[] details = result.getDetails();
			
			for ( RecurringDetail detail : details ){
				System.out.println( "*****************" );
				System.out.println( detail.getName() );
				System.out.println( detail.getRecurringDetailReference() );
				System.out.println( detail.getVariant() );
				
				if ( detail.getCard() != null ){
					System.out.println( detail.getCard().getBrand() );
					System.out.println( detail.getCard().getExpiryMonth() );
					System.out.println( detail.getCard().getExpiryYear() );
					System.out.println( detail.getCard().getHolderName() );
					System.out.println( detail.getCard().getNumber() );
				}
			}
				

		}
		catch ( Exception ex ){
			ex.printStackTrace();
		}

	}

}
