import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./login.css"

export default function Login(){
    // champs du formulaire
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    //  pour afficher les messages
    const [message, setMessage] = useState("");

    // pour le délai de traitement
    const [loading, setLoading] = useState(false);

    // fonction appelée à la soumission du formulaire
    const handleSubmit = async(e : React.FormEvent) => {
        e.preventDefault(); // empêche le rechargement de la page
        setLoading(true);
        setMessage("") // initialisation du message

        try{
            interface LoginResponse {
                token: string;
            }

            const response = await axios.post<LoginResponse>(
                "http://localhost:8080/api/auth/login",
                {
                    email,
                    motDePasse : password,
                    roles:["utilisateur"]
                },
                {
                    withCredentials:true,
                }
            );
            setMessage("Connexion réussie");
            const token = response.data.token;
            localStorage.setItem("token", token);
            console.log(response.data);
        }
        catch(error:any){
            if(error.response && error.response.data){
                setMessage(error.response.data.message);
            }
            else{
                setMessage("Une erreur est survenue");
            }
        }
        finally{
            setLoading(false);
        }
    };
    return(
        <div className="conteneur_login">
            <div id="section_connexion">
                <h2>Connectez-vous</h2>
                
                <form className="formulaire_connexion" onSubmit={handleSubmit}>
                    <div className="form-group">
                        <input type="email" 
                        id="email" 
                        name="email" 
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        autoComplete="email" 
                        placeholder="Identifiant" 
                        required/>
                    </div>
                    
                    <div className="form-group">
                        <input type="password" 
                        id="password" 
                        name="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)} 
                        autoComplete="current-password" 
                        placeholder="Mot de passe" 
                        required/>
                    </div>
                    
                    <div id="remember-forgot">
                        <div className="remember-group">
                            <input type="checkbox" 
                            id="remember"/>
                            <label htmlFor="remember">
                                Se souvenir de moi
                            </label>
                        </div>
                        <a href="#">Mot de passe oublié ?</a>
                    </div>
                    
                    <button id="connexion"  type="submit" disabled={loading}>
                        {loading?"En cours" : "Connexion"}
                    </button>
                    
                    <div id="continuer-avec">
                        <p>Ou continuer avec</p>
                        <div className="social-links">
                            <a href="#">Google</a>
                            <a href="#">Apple</a>
                            <a href="#">LinkedIn</a>
                        </div>
                    </div>
                    
                    <div className="signup-link">
                        <p>Vous n'avez pas de compte ? <Link to="/register">Créer un compte</Link></p>
                    </div>
                    {message && <p className="message">{message}</p>}
                </form>
            </div>
        </div>
    );
}