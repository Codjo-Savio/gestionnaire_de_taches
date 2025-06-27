import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
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
        <div className="conteneur">
        <div id="section_slogan">
            <h1>GalaxyTasks</h1>
            <ol>
                <li>Gérez efficacement vos projets</li>
                <li>Structurez vos tâches</li>
                <li>Dans un environnement approprié</li>
                <li>Alors ?... Ne tardez plus !</li>
                <li>Rejoignez-nous...</li>
            </ol>
        </div>
        <div id="section_inscription">
            
            <form className="formulaire_inscription" onSubmit={handleSubmit}>
                <h2>Inscrivez-vous</h2>
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
                    <input type="password" 
                    id="password" 
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    name="password" 
                    autoComplete="current-password" 
                    placeholder="Mot de passe" 
                    required/>
                </div>

                <div className="form-group">
                    <input type="password" 
                    id="confirm_password"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)} 
                    name="password" 
                    autoComplete="current-password" 
                    placeholder="Confirmer mot de passe" 
                    required/>
                </div>
                
                <div id="remember-forgot">
                    <div className="remember-group">
                        <input type="checkbox" 
                        id="remember"/>
                        <label htmlFor="remember">Se souvenir de moi</label>
                    </div>
                </div>
                
                <button id="Inscription" type="submit" disabled={loading}>
                    {loading? "En cours" : "Inscription"}
                </button>
                <div id="continuer-avec">
                    <p>Ou continuer avec</p>
                    <div className="social-links">
                        <a href="#">Google</a>
                        <a href="#">Apple</a>
                        <a href="#">LinkedIn</a>
                    </div>
                </div>
               
            {message && <p className="message">{message}</p>}
            </form>
            </div> 
        </div>
    );
}