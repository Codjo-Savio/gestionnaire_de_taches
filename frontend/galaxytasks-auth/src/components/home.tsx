import { useNavigate } from "react-router-dom";
import "./home.css"
import { useState } from "react";
import { useTheme } from "../context/ThemeContext";
import { Link } from 'react-scroll';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

export default function Home(){

    const navigate = useNavigate();

    const toRegister = () => {
        navigate("/register")
    };

    const toLogin = () => {
        navigate("/login")
    };

    const { theme, toggleTheme } = useTheme();
    const [menuOpen, setMenuOpen] = useState(false);

    const handleThemeChange = (selected: "light" | "dark") => {
    if (selected !== theme) toggleTheme();
        setMenuOpen(false);
    };

    return(
        <div className="conteneur-home">
            <header className="header-home">
                <div className="app_name">
                    <h2 className="h2-home">SITgalaxy Corporation</h2>
                    <h1 className="h1-home">GalaxyTasks</h1>
                </div>
                <div className="nav">
                    <Link
                        className="a-home"
                        to="section-home-2"
                        smooth={true}
                        duration={500}>
                            Solution
                    </Link>
                    <a className="a-home" href="#">FAQ</a>
                    <a className="a-home" href="#">Contact</a>
                    <button className="themeButton" onClick={() => setMenuOpen(!menuOpen)}>
                            Theme
                    </button>
                </div>
                
            </header>
            {menuOpen && (
                <div className="opened">
                    <button className="lightButton"
                        onClick={() => handleThemeChange("light")}>
                        Clair
                    </button>
                    <button className="darkButton"
                        onClick={() => handleThemeChange("dark")}>
                        Sombre
                    </button>
                </div>
            )}
            <section className="section-home">
                <div className="comment">
                    <h1 className="h1-home">
                        Optimisez le suivi de vos projets <br/> Soyez plus productifs <br/> Avec GalaxyTasks
                    </h1>
                    <div className="options">
                        <button className="register-login" onClick={toLogin}> Mon compte </button>
                        <button className="register-login" onClick={toRegister}> Nous rejoindre </button>
                       <Link
                        className="savoir-plus"
                        to="section-home-2"
                        smooth={true}
                        duration={500}>
                            En savoir plus
                        </Link>
                    </div>
                </div>
                <aside className="aside-register-presentation">
                </aside>
            </section>

            <section className="section-home-2">
                <div className="solution">
                    <h1 className="h1-solution">Solution</h1>
                    <p className="p-solution">
                        Étudiants, équipes, entreprises, particuliers...
                        Toutes ces entités ont besoin d'un suivi optimisé de leurs projets dans un environnement à la fois simple et puissant.
                        C'est ce que nous proposons
                    </p>
                </div>
                <div className="statements">
                    <p className="p-statements">
                        Vous travaillez en équipe ?<br/>
                        Synchronisez vos tâches, et suivez l'avancement en temps réel.
                    </p>
                    <p className="p-statements">
                        GalaxyTasks<br/>
                        Organisez mieux, soyez plus productifs
                        et pourquoi pas en équipe ?
                    </p>
                    <p className="p-statements">
                        Besoin d'une roadmap pour votre projet en équipe ?<br/>
                        Organisez une réunion, et décidez.
                    </p>
                </div>
            </section>

            <section className="section-home-3">
                <h1 className="h1-title">
                    Synchronisation des tâches <br/>
                    Vous travaillez en équipe ? <br/>
                    Alors vous pouvez...
                </h1>
                <h2 className="h2-actions"><FontAwesomeIcon icon="users" size="1x" /> Inviter des membres sur votre projet</h2>
                <h2 className="h2-actions"><FontAwesomeIcon icon="clipboard-list" size="1x"/> Répartir les tâches</h2>
                <h2 className="h2-actions"><FontAwesomeIcon icon="comments" size="1x"/> Organiser des réunions et correspondre par chat</h2>
                <h2 className="h2-actions"><FontAwesomeIcon icon="rocket" size="1x"/> Boostez votre productivité, construisez la meilleure des équipes</h2>
            </section>

            <section className="section-home-4">
                <h1 className="h1-title">
                    Synchronisation des tâches <br/>
                    Vous travaillez en équipe ? <br/>
                    Alors vous pouvez...
                </h1>
            </section>
        </div>
    );
}