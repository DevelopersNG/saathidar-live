package com.sathidar.EntityMangerFactory;

	import javax.persistence.EntityManager;
    import javax.persistence.PersistenceContext;
    import org.springframework.stereotype.Service;
    import com.sathidar.model.AdminKycModel;

		@Service
		public class adminKycEntityManagerFactory {

			@PersistenceContext
			private EntityManager em;
			
			public String uploadPhotos(AdminKycModel adminKycModel) {
				String results="";
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return results;
			}
		}



