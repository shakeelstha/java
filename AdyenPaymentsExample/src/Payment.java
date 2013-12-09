

import java.util.Date;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

import com.adyen.services.common.Amount;
import com.adyen.services.common.Installments;
import com.adyen.services.payment.AnyType2AnyTypeMapEntry;
import com.adyen.services.payment.Card;
import com.adyen.services.payment.PaymentLocator;
import com.adyen.services.payment.PaymentPortType;
import com.adyen.services.payment.PaymentRequest;
import com.adyen.services.payment.PaymentResult;
import com.adyen.services.payment.Recurring;


public class Payment {
    
	public static void main ( String[] args ) {
		try {
			
			PaymentPortType ws = new PaymentLocator().getPaymentHttpPort( new java.net.URL( Credentials.test_address) );
			
			//Basic HTTP Authentication:
			( (Stub) ws )._setProperty( Call.USERNAME_PROPERTY, Credentials.ws_user );
			( (Stub) ws )._setProperty( Call.PASSWORD_PROPERTY, Credentials.pwd_user );
			
			// Payment data
			PaymentRequest request = new PaymentRequest();
			request.setMerchantAccount( Credentials.merchant_account );
			request.setAmount( new Amount( "GBP", 199) );
			request.setReference( "Payment Test 1" );
			
			//request.setInstallments( new Installments( (short) 2) );
			//request.setShopperEmail( "shopperemail@server.com" );
			//request.setShopperReference( "shopperreference" );
			//request.setFraudOffset( -100 );
			
			Card card = new Card();
			card.setHolderName( "John Smith" );
			card.setCvc( "737" );
			card.setExpiryMonth( "06" );
			card.setExpiryYear( "2016" );
			card.setNumber( "4111111111111111" );
			request.setCard( card );
			
			PaymentResult result = ws.authorise( request );
			
			System.out.println( new Date() );
			
			System.out.println( result.getPspReference() );
			System.out.println( result.getResultCode() );
			System.out.println( result.getAuthCode() );
			System.out.println( result.getRefusalReason() );

		}
		catch ( Exception ex ) {
			ex.printStackTrace();
		}

	}

}
