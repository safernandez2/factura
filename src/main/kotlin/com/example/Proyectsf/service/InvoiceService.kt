package com.example.Proyectsf.service


import com.example.Proyectsf.model.Invoice
import com.example.Proyectsf.repository.ClientRepository
import com.example.Proyectsf.repository.InvoiceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class InvoiceService {
    @Autowired
    lateinit var invoiceRepository: InvoiceRepository
    @Autowired
    lateinit var clientRepository: ClientRepository

    fun save (invoice:Invoice):Invoice{
        try {
            clientRepository.findById(invoice.clientId)
                ?: throw Exception("Cliente no existe")
            return invoiceRepository.save(invoice)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }

    }
    fun list ():List<Invoice>{
        return invoiceRepository.findAll()
    }
    fun update(invoice:Invoice):Invoice{
        try {
        invoiceRepository.findById(invoice.id)
            ?: throw Exception("ID no existe")
        return  invoiceRepository.save(invoice)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }

    }

    fun updateTotal(invoice:Invoice): Invoice {
        try{
            val response = invoiceRepository.findById(invoice.id)
                ?: throw Exception("ID no existe")
            response.apply {
                total=invoice.total
            }
            return invoiceRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}