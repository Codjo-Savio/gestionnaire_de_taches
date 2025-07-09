import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import feather from "feather-icons";
import './register.css';

export default function Register(){
    // champs du formulaire
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    // pour l'affichage d'un message ou d'une erreur
    const [message, setMessage] = useState("");

    // pour le loading
    const [loading, setLoading] = useState(false);

    // pour la navigation entre les pages
    const navigate = useNavigate();

    const [showPassword, setShowPassword] = useState(false);

    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    useEffect(() => {
        const iconContainer = document.querySelector(".password-icon");
        const iconContainer2 = document.querySelector(".confirm-password-icon");
        if (iconContainer) {
            iconContainer.innerHTML = `<i data-feather="${showPassword ? 'eye-off' : 'eye'}"></i>`;
            feather.replace();
        }
        if (iconContainer2) {
            iconContainer2.innerHTML = `<i data-feather="${showConfirmPassword ? 'eye-off' : 'eye'}"></i>`;
            feather.replace();
        }
    });

    const togglePasswordVisibility = () => {
        setShowPassword(prev => !prev);
    };

     const toggleConfirmPasswordVisibility = () => {
        setShowConfirmPassword(prev => !prev);
    };

    // fonction appelée à la soumission du formulaire
    const handleSubmit = async(e: React.FormEvent) =>{
        e.preventDefault(); // empêche le rechergement de la page
        setMessage(""); // reset message
        setLoading(true); // activation du loading
        try{
            if (password !== confirmPassword) {
                setMessage("Les mots de passe ne correspondent pas.");
                setLoading(false);
                return;
            }
            const response = await axios.post(
                "http://localhost:8080/api/auth/register",
                {
                    username,
                    email,
                    password,
                    roles:["utilisateur"]
                },
                {
                    withCredentials:true,
                }
            );
            console.log(response.data);
            setTimeout(() => navigate("/login"), 2000); // 2 secondes
            //navigate("/login")

        }  catch(error:any){
            if(error.response && error.response.data){
                setMessage(error.response.data.message);
            }
            else{
                setMessage("Une erreur est survenue");
            }
        }finally{
            setLoading(false);
        }
       
    };
    return(
        <div className="conteneur-register">
        <div id="section_slogan">
            <h1 className="h1-register">GalaxyTasks</h1>
            <ol className="ol-register">
                <li className="li-register">Gérez efficacement vos projets</li>
                <li className="li-register">Structurez vos tâches</li>
                <li className="li-register">Dans un environnement approprié</li>
                <li className="li-register">Alors ?... Ne tardez plus !</li>
                <li className="li-register">Rejoignez-nous...</li>
            </ol>
        </div>
        <div id="section_inscription">
            
            <form className="formulaire_inscription" onSubmit={handleSubmit}>
                <h2 className="h2-register">Inscrivez-vous</h2>
                <div className="form-group">
                    <input 
                    type="email" 
                    id="email" 
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    name="email"
                    autoComplete="email" 
                    placeholder="email" 
                    required/>
                </div>

                <div className="form-group">
                    <input type="text"  
                    id="username" 
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    name="username" 
                    autoComplete="username" 
                    placeholder="nom d'utilisateur" 
                    required/>
                </div>
                
                <div className="form-group">
                    <input
                    type={showPassword ? "text" : "password"} 
                    id="password" 
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    name="password" 
                    autoComplete="current-password" 
                    placeholder="Mot de passe" 
                    required/>
                    <div className="password-icon" 
                        onClick={togglePasswordVisibility}
                        role="button">
                    </div>
                </div>

                <div className="form-group">
                    <input
                    type={showConfirmPassword ? "text" : "password"} 
                    id="confirm_password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)} 
                    name="password" 
                    autoComplete="current-password" 
                    placeholder="Confirmer mot de passe" 
                    required/>
                    <div className="confirm-password-icon" 
                        onClick={toggleConfirmPasswordVisibility}
                        role="button">
                    </div>
                </div>
                
                <button className="register-button" type="submit" disabled={loading}>
                    {loading? "En cours" : "Inscription"}
                </button>
                <div id="continuer-avec">
                    <p>Ou continuer avec</p>
                    <div className="social-links">
                        <a className="a-register" href="#">Google</a>
                        <a className="a-register" href="#">Apple</a>
                        <a className="a-register" href="#">LinkedIn</a>
                    </div>
                </div>
               
            {message && <p className="message">{message}</p>}
            </form>
            </div> 
        </div>
    );
}