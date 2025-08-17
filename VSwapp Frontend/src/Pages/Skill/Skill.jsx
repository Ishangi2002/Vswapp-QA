import React from 'react'
import { Link } from "react-router-dom";
import { UserCircleIcon } from "@heroicons/react/24/solid";
import SkillCatalog from './SkillCatalog';
import { HowToSuggestSkill } from './HowToSuggestSkill';
import { Footer } from '../../Components/Footer';
import Navbar2 from '../../Components/Navbar2';


export const Skill = () => {
  return (
        <div className="bg-gradient-to-b from-[#090e2d] to-[#111827] min-h-screen text-white">

    <div className="flex-1">
         < Navbar2 /> 
         < SkillCatalog />
         < HowToSuggestSkill />
         
         
         
    </div>
  < Footer />
    </div>
  )
}

export default Skill