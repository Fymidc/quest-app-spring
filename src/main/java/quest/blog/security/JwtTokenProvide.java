package quest.blog.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvide {
    @Value("${questapp.app.secret}")
    private String APP_SECRET;

    @Value("${questapp.expires.in}")
    private Long EXPIRES_IN;   
    
    public String generateJwtToken(Authentication auth){
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        Date expireDate = new Date(new Date().getTime()+EXPIRES_IN);
        
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                //key nezaman oluşturuldu---nezaamn expire oldu
                .setIssuedAt(new Date()).setExpiration(expireDate)
                //token oluşturan algoritme key kullanarak yapar--compact ise tokeni oluşturur
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
    }

    //token den user id yi almak
    Long getUserIdFromJwt(String token){   //app secret veriyoruzki aynı algrtma ile çözsün
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }

    //oluştulan token validate edilmeli doğrumu değilmi

    boolean validateToken(String token){
        try{ //eger verilen appsecret ile çözümlerse doğrudur zaten
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            //token doğru ama expire olmuştu kontrol için method kullandık
            return !isTokenExpired(token);//expired ise true dönücek tersini alarak false dönmesi sağladık
        }catch(SignatureException e){
            return false;
        
        }catch(ExpiredJwtException e){
            return false;
        }
        catch(MalformedJwtException e){
            return false;
        }
        catch(UnsupportedJwtException e){
            return false;
        }
        catch(IllegalArgumentException e){
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();

        //eğer bugün ayın 10 ise expiration date is 6 ise 
        return expiration.before(new Date());//6 before 10 olduğu için true dönecek
    }

}

//24,09 da kaldım 
